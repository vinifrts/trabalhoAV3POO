import Interfaces.Promocao;

public abstract class Produto implements Promocao {
    private static int contadorIds = 1;
    private int id;
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.id = contadorIds++;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco + +
                '}';
    }

    @Override
    public void aplicarDesconto(double porcentagem) {
        preco -= preco * (porcentagem / 100);
        System.out.println("Desconto aplicado no prato " + nome + ". Novo preço: R$" + preco);
    }

}
