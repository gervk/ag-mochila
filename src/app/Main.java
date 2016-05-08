package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("ventana.fxml"));
        stage.setScene(new Scene(root));
        stage.show();

        stage.setResizable(false);
    }
/*
    public static void main(String[] args){
        int vuelta = 0;

        //Poblacion inicial generada al azar
        Poblacion poblacion = new Poblacion(Config.getInstance().tamanioPobInicial);
        poblacion.generar();
        poblacion.setNumero(vuelta);

        //Criterio de paro por cantidad de vueltas
        while(vuelta < Config.getInstance().cantVueltas){
            vuelta++;

            poblacion = Algoritmos.evolucionarPoblacion(poblacion);
            poblacion.setNumero(vuelta);
            System.out.println(poblacion.toString());


        }
    }
*/

}
