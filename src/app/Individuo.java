package app;

public class Individuo {
    private int cantGenes = Config.getInstance().items.length;
    private int[] genes = new int[cantGenes];

    private int peso = -1;
    private int aptitud = -1;

    //Setea aleatoriamente en 1 o 0 el valor de cada gen (presencia del item n)
    public void generar() {
        for(int n = 0; n < cantGenes; n++){
            setGen(n, (int) Math.round(Math.random()));
        }
    }

    public int getCantGenes(){
        return cantGenes;
    }

    public int getGen(int numero){
        return genes[numero];
    }

    public void setGen(int numero, int valor){
        genes[numero] = valor;
    }

    public int getAptitud(){
        if(aptitud == -1){
            aptitud = FuncionAptitud.getAptitud(this);
        }
        return aptitud;
    }

    //Ejemplo: "1100101011"
    public String getCromosoma(){
        String cromosoma = "";

        for(int n = 0; n < cantGenes; n++){
            cromosoma = cromosoma + String.valueOf(getGen(n));
        }

        return cromosoma;
    }

    public int getPeso(){
       if(peso == -1){
           for(int n = 0; n < cantGenes; n++){
               //peso del item n * 1 o 0 segun el gen
               peso = peso + Config.getInstance().getItem(n).peso * getGen(n);
           }
       }

        return peso;
    }

    public String toString(){
        String string = "Cromosoma = " + getCromosoma() + " Aptitud = " + String.valueOf(getAptitud()) + " Peso = " + getPeso();

        return string;
    }
}
