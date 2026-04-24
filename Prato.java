import Interfaces.Compravel;

public class Prato extends Produto implements Compravel {

    public Prato(String nome, double preco) {
        super(nome, preco);
    }
    @Override
    public double calcularPreco() {
        return getPreco();
    }


}
