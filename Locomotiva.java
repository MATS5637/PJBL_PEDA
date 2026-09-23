public class Locomotiva extends Vagao {
    private double potencia;

    public Locomotiva(double comprimento, double peso, double potencia) {
        super(comprimento, peso);
        this.potencia = potencia;
    }

    public double getPotencia() {
        return potencia;
    }

    @Override
    public void imprime() {
        System.out.println("Tipo: Locomotiva.");
        System.out.println("Potência: " + potencia + " HP.");
        super.imprime();
    }
}