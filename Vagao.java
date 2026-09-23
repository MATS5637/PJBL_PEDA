import java.io.Serializable;

public abstract class Vagao implements Serializable {
    protected double comprimento;
    protected double peso;

    public Vagao(double comprimento, double peso) {
        this.comprimento = comprimento;
        this.peso = peso;
    }

    public double getComprimento() {
        return comprimento;
    }

    public double getPeso() {
        return peso;
    }

    public void imprime(){
        System.out.println("Comprimento: " + getComprimento());
        System.out.println("Peso: " + getPeso());
    }
}
