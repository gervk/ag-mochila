package app;

public class Config {
    public int tamanioPobInicial = 20;

    public Item[] items = new Item[10];
    public int pesoMax = 130;
    public int factorExcesoPeso = 5;

    public double probCruce = 0.5;
    public double probMutacion = 0.5;
    public int tamanioPobTorneo = 5;

    public int cantVueltas = 15;

    private static Config INSTANCE = null;

    private Config(){
        crearItems();
    }

    public static Config getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

    private void crearItems(){
        items[0] = new Item(18, 60);
        items[1] = new Item(15, 55);
        items[2] = new Item(54, 100);
        items[3] = new Item(33, 77);
        items[4] = new Item(18, 60);
        items[5] = new Item(20, 65);
        items[6] = new Item(42, 85);
        items[7] = new Item(7, 45);
        items[8] = new Item(28, 72);
        items[9] = new Item(33, 77);
    }

    public Item getItem(int numero){
        return items[numero];
    }
}
