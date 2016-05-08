package app;

public class Algoritmos {

    //Seleccion, cruzamiento y mutacion
    public static Poblacion evolucionarPoblacion(Poblacion poblacion){
        Poblacion nuevaPoblacion = new Poblacion(poblacion.getTamanio());

        for(int n = 0; n < poblacion.getTamanio(); n++){
            //Selecciona 2 individuos de la poblacion a evolucionar
            Individuo individuo1 = seleccionTorneo(poblacion);
            Individuo individuo2 = seleccionTorneo(poblacion);

            //Los cruza creando un nuevo individuo
            Individuo nuevoIndividuo = cruzar(individuo1, individuo2);

            //Mutacion del nuevo individuo
            nuevoIndividuo = mutar(nuevoIndividuo);

            nuevaPoblacion.agregarIndividuo(n, nuevoIndividuo);
        }

        return nuevaPoblacion;
    }

    //Seleccion por torneo
    private static Individuo seleccionTorneo(Poblacion poblacion){
        //Genera una poblacion de cierto tamaño
        Poblacion pobTorneo = new Poblacion(Config.getInstance().tamanioPobTorneo);

        //Le agrega individuos aleatorios de la poblacion original
        for(int n = 0; n < pobTorneo.getTamanio(); n++){
            int numeroN = (int) (Math.random() * poblacion.getTamanio());
            pobTorneo.agregarIndividuo(n, poblacion.getIndividuo(numeroN));
        }

        //Devuelve el mas apto de la poblacion torneo
        Individuo elMasApto = pobTorneo.getMasApto();

        return elMasApto;
    }

    //Cruza binominal por azar
    private static Individuo cruzar(Individuo indiv1, Individuo indiv2){
        Individuo nuevoIndiv = new Individuo();

        for(int n = 0;n < nuevoIndiv.getCantGenes(); n++){
            //Genera un num aleatorio y ,segun la prob de cruce,
            //el gen toma el mismo valor que el gen del indiv 1 o el del indiv2
            if(Math.random() <= Config.getInstance().probCruce){
                nuevoIndiv.setGen(n, indiv2.getGen(n));
            }
            else{
                nuevoIndiv.setGen(n, indiv1.getGen(n));
            }
        }

        return nuevoIndiv;
    }

    private static Individuo mutar(Individuo indiv){
        for(int n = 0; n < indiv.getCantGenes(); n++){
            //Genera un num aleatorio y, segun la prob de mutacion, invierte el valor del gen
            if(Math.random() <= Config.getInstance().probMutacion){
                if(indiv.getGen(n) == 0) {
                    indiv.setGen(n, 1);
                }
                else{
                    indiv.setGen(n, 0);
                }
            }
        }

        return indiv;
    }
}
