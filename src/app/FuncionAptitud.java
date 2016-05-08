package app;

import java.util.ArrayList;
import java.util.List;

public class FuncionAptitud {

    public static int getAptitud(Individuo individuo){
        //Se van guardando las ganancias y pesos agregados para
        //el control de que no se repitan
        List<Integer> gananciasAgregadas = new ArrayList<>();
        List<Integer> pesosAgregados = new ArrayList<>();

        Config config = Config.getInstance();

        int aptitud = 0;
        int multaPeso = 0;

        Item itemN;

        //Sumatoria de ganancinas segun los genes
        for(int n = 0; n < individuo.getCantGenes(); n++){
            itemN = config.getItem(n);

            //Suma si el gen es 1 y si no conto otro item con la misma ganancia o peso (no se pueden repetir)
            if(individuo.getGen(n) == 1 &&
                    !gananciasAgregadas.contains(itemN.ganancia) && !pesosAgregados.contains(itemN.peso)){

                aptitud = aptitud + itemN.ganancia * individuo.getGen(n);
                gananciasAgregadas.add(itemN.ganancia);
                pesosAgregados.add(itemN.peso);
            }
        }

        //Control de restriccion del peso max
        int excesoPeso = individuo.getPeso() - config.pesoMax;
        if(excesoPeso > 0){
            multaPeso = excesoPeso * config.factorExcesoPeso;
        }
        aptitud = aptitud - multaPeso;

        return aptitud;
    }
}
