package app;

public class FuncionAptitud {

    public static int getAptitud(Individuo individuo){
        //TODO faltan las restricciones de no tener repetidos
        Config config = Config.getInstance();

        int aptitud = 0;
        int multaPeso = 0;

        //Sumatoria de ganancinas * gen
        for(int n = 0; n < individuo.getCantGenes(); n++){
            aptitud = aptitud + config.getItem(n).ganancia * individuo.getGen(n);
        }

        //Control de restriccion del peso
        int excesoPeso = individuo.getPeso() - config.pesoMax;
        if(excesoPeso > 0){
            multaPeso = excesoPeso * config.factorExcesoPeso;
        }
        aptitud = aptitud - multaPeso;

        return aptitud;
    }
}
