public class Passageiro extends Vagao {
    private int passageiros;

    public Passageiro(double comprimento, double peso, int passageiros) {
        super(comprimento, peso);
        this.passageiros = passageiros;
    }

    public int getPassageiros() {
        return passageiros;
    }

    @Override
    public void imprime() {
        System.out.println("Tipo: Locomotiva.");
        System.out.println("Passageiros: " + getPassageiros());
        super.imprime();
    }
}