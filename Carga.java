public class Carga extends Vagao {
    private double carga;

    public Carga(double comprimento, double peso) {
        super(comprimento, peso);
        this.carga = peso*0.75;
    }

    public double getCarga() {
        return carga;
    }

    @Override
    public void imprime() {
        System.out.println("Tipo: Carga.");
        System.out.println("Carga: " + getCarga() + "ton");
        super.imprime();
    }
}