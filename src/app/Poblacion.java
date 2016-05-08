package app;

public class Poblacion {
    private int numero;
    private int tamanio;
    private Individuo[] individuos;

    public Poblacion(int tamanio){
        this.tamanio = tamanio;
        individuos = new Individuo[tamanio];
    }

    //Genera aleatoriamente los individuos
    public void generar(){
        for (int n = 0; n < tamanio; n++){
            Individuo nuevoIndividuo = new Individuo();
            nuevoIndividuo.generar();
            agregarIndividuo(n, nuevoIndividuo);
        }
    }

    public void agregarIndividuo(int numero, Individuo individuo){
        individuos[numero] = individuo;
    }

    public Individuo getIndividuo(int numero){
        return individuos[numero];
    }

    public int getTamanio(){
        return tamanio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Individuo getMasApto(){
        Individuo masApto = individuos[0];

        for(int n = 0; n < tamanio; n++){
            if(getIndividuo(n).getAptitud() > masApto.getAptitud()){
                masApto = getIndividuo(n);
            }
        }

        return masApto;
    }

    public int getAptitudMasApto(){
        return getMasApto().getAptitud();
    }

    public String getCromosomaMasApto(){
        return getMasApto().getCromosoma();
    }

    public int getPesoMasApto(){
        return getMasApto().getPeso();
    }

    public String toString() {
        return "Poblacion:" + numero + " Mejor individuo:" + getMasApto().toString();
    }

}
