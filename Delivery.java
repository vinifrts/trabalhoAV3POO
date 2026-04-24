import java.util.Scanner;
import Interfaces.Entrega;

public abstract class Delivery implements Entrega {
    protected String enderecoCliente;
    protected double precoEntrega;

    public Delivery(String enderecoCliente, double precoEntrega) {
        this.enderecoCliente = enderecoCliente;
        this.precoEntrega = precoEntrega;
    }

    @Override
    public void pedirEndereco() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o endereço de entrega: ");
        this.enderecoCliente = scanner.nextLine();
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public double getPrecoEntrega() {
        return precoEntrega;
    }

    public void setPrecoEntrega(double precoEntrega) {
        this.precoEntrega = precoEntrega;
    }
}
