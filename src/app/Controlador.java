package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controlador {
    Config config = Config.getInstance();

    //Lista con las poblaciones que se generan
    ObservableList<Poblacion> poblaciones = FXCollections.observableArrayList();

    @FXML
    private TextField txtProbCruce;

    @FXML
    private TextField txtCantCorridas;

    @FXML
    private TableColumn<Poblacion, Integer> colAptitud;

    @FXML
    private TableView<Poblacion> lstReultados;

    @FXML
    private TableColumn<Poblacion, Integer> colNum;

    @FXML
    private TextField txtTamanioPobInicial;

    @FXML
    private Button btnEjecutar;

    @FXML
    private TextField txtProbMutacion;

    @FXML
    private TextField txtTamanioPobTorneo;

    @FXML
    private TableColumn<Poblacion, String> colCromosoma;

    @FXML
    private TableColumn<Poblacion, Integer> colPeso;

    @FXML
    private BarChart<String, Integer> grafico;

    @FXML
    //Esto se ejecuta al abrir la ventana
    void initialize(){
        cargaValoresDefault();

        configLista();
    }

    @FXML
    void ejecutar(ActionEvent event) {
        //Limpia la lista de poblaciones
        lstReultados.getItems().clear();

        //Carga en la config los valores ingresados
        config.tamanioPobInicial = Integer.parseInt(txtTamanioPobInicial.getText());
        config.tamanioPobTorneo = Integer.parseInt(txtTamanioPobTorneo.getText());
        config.probCruce = Double.parseDouble(txtProbCruce.getText().replace(",", "."));
        config.probMutacion = Double.parseDouble(txtProbMutacion.getText().replace(",", "."));
        config.cantVueltas = Integer.parseInt(txtCantCorridas.getText());

        int vuelta = 0;

        //Poblacion inicial generada al azar
        Poblacion poblacion = new Poblacion(config.tamanioPobInicial);
        poblacion.generar();
        poblacion.setNumero(vuelta);
        poblaciones.add(poblacion);

        //Criterio de paro por cantidad de vueltas
        while(vuelta < Config.getInstance().cantVueltas){
            vuelta++;

            poblacion = Algoritmos.evolucionarPoblacion(poblacion);
            poblacion.setNumero(vuelta);
            poblaciones.add(poblacion);
            System.out.println(poblacion.toString());
        }

        completarGrafico();
    }

    private void cargaValoresDefault(){
        txtTamanioPobInicial.setText(String.valueOf(config.tamanioPobInicial));
        txtTamanioPobTorneo.setText(String.valueOf(config.tamanioPobTorneo));
        txtProbCruce.setText(String.valueOf(config.probCruce));
        txtProbMutacion.setText(String.valueOf(config.probMutacion));
        txtTamanioPobInicial.setText(String.valueOf(config.cantVueltas));
        txtCantCorridas.setText(String.valueOf(config.cantVueltas));
    }

    private void configLista(){
        //Binding de las columnas
        lstReultados.setItems(poblaciones);
        colNum.setCellValueFactory(new PropertyValueFactory<Poblacion,Integer>("numero"));
        colAptitud.setCellValueFactory(new PropertyValueFactory<Poblacion,Integer>("aptitudMasApto"));
        colCromosoma.setCellValueFactory(new PropertyValueFactory<Poblacion,String>("cromosomaMasApto"));
        colPeso.setCellValueFactory(new PropertyValueFactory<Poblacion,Integer>("pesoMasApto"));
    }

    private void completarGrafico(){
        grafico.getData().clear();

        //Valores (x,y) para el grafico (num poblacion, max aptitud)
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < poblaciones.size(); i++) {
            series.getData().add(new XYChart.Data<>(String.valueOf(poblaciones.get(i).getNumero()), poblaciones.get(i).getAptitudMasApto()));
        }

        grafico.getData().add(series);
    }
}
