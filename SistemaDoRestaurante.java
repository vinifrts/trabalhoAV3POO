import Exceptions.LoginInvalido;
import Exceptions.PermissaoNegada;
import Interfaces.Entrega;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class SistemaDoRestaurante {
    private Scanner scanner;
    private Map<String, Usuario> usuarios;
    private List<Entrega> listaEntregas;
    private Map<String, Funcionario> funcionarios;
    private Usuario usuarioLogado;
    private Map<Integer, Prato> Cardapio;

    public SistemaDoRestaurante() {
        scanner = new Scanner(System.in);
        listaEntregas = new ArrayList<>();
        usuarios = new HashMap<>();
        Cardapio = new HashMap<>();
        usuarios.put("vinicius@gmail.com", new Gerente("Vini", "vinicius@gmail.com"));
    }

    public void iniciar() {
        boolean executando = true;
        while (executando) {
            try {
                System.out.println("=== VINI´S RESTAURANTE ===");
                System.out.println("1 - Inicializar o Sistema");
                System.out.println("2 - Fechar o Sistema");
                System.out.print("Escolha uma opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        menuLogin();
                        break;
                    case 2:
                        executando = false;
                        System.out.println("SISTEMA DO RESTAURANTE VINI´S ENCERRADO");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void menuLogin() throws LoginInvalido {
        System.out.println("=== FAÇA O LOGIN ===");
        System.out.println("1 - Gerente");
        System.out.println("2 - Funcionário");
        System.out.print("Escolha o tipo de usuário: ");

        try {
            int tipoUsuario = Integer.parseInt(scanner.nextLine());
            System.out.print("Email: ");
            String email = scanner.nextLine();
            realizarLogin(tipoUsuario, email);

        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido!");
        }
    }

    private void realizarLogin(int tipoUsuario, String email) throws LoginInvalido {
        Usuario usuario = usuarios.get(email);
        if (usuario == null) {
            throw new LoginInvalido("Usuário não encontrado!");
        }


        switch (tipoUsuario) {
            case 1:
                if (!(usuario instanceof Gerente)) {
                    throw new LoginInvalido("Acesso não autorizado para Gerente");
                }
                break;
            case 2:
                if (!(usuario instanceof Funcionario)) {
                    throw new LoginInvalido("Acesso não autorizado para Funcionário!");
                }
                break;
            default:
                throw new LoginInvalido("Tipo de usuário inválido!");
        }

        usuarioLogado = usuario;
        System.out.println("Login realizado com sucesso! Bem-vindo, " + usuario.getNome());
        exibirMenuPrincipal(tipoUsuario);
    }

    private void exibirMenuPrincipal(int tipoUsuario) {
        boolean executando = true;
        while (executando) {
            try {
                System.out.println("=== MENU PRINCIPAL ===");
                switch (tipoUsuario) {
                    case 1:
                        executando = menuGerente();
                        break;
                    case 2:
                        executando = menuFuncionario();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private boolean menuGerente() {
        System.out.println("1 - Cadastrar novo funcionário");
        System.out.println("2 - Cadastrar novo prato");
        System.out.println("3 - Listar todos os pratos");
        System.out.println("4 - Aplicar desconto em produto");
        System.out.println("5 - Adicionar serviço de entrega");
        System.out.println("6 - Exibir todas as entregas");
        System.out.println("7 - Sair");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1:
                    cadastrarFuncionario();
                    return true;
                case 2:
                    cadastrarPrato();
                    return true;
                case 3:
                    listarPratos();
                    return true;
                case 4:
                    aplicarDescontoProduto();
                    return true;
                case 5:
                    adicionarServicoEntrega();
                    return true;
                case 6:
                    exibirERealizarEntregas();
                    return true;
                case 7:
                    System.out.println("Logout realizado com sucesso!");
                    return false;
                default:
                    System.out.println("Opção inválida!");
                    return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido!");
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return true;
        }
    }

    private boolean menuFuncionario() {
        System.out.println("1 - Cadastrar um novo prato");
        System.out.println("2 - Listar todos os Pratos");
        System.out.println("3 - Logout");
        System.out.print("Escolha uma opção: ");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1:
                    cadastrarPrato();
                    return true;
                case 2:
                    listarPratos();
                    return true;
                case 3:
                    System.out.println("Operação Finalizada");
                    return false;
                default:
                    System.out.println("Opção inválida!");
                    return true;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return true;
        }
    }

    void cadastrarFuncionario() throws PermissaoNegada {
        if (!(usuarioLogado instanceof Gerente)) {
            throw new PermissaoNegada("Apenas o Dono pode cadastrar funcionários!");
        }else {

            System.out.println("=== CADASTRO DE FUNCIONÁRIO ===");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Cargo: ");
            String cargo = scanner.nextLine();

            Funcionario novoFuncionario = new Funcionario(nome, email,cargo);
            usuarios.put(email, novoFuncionario);
            System.out.println("Funcionário cadastrado com sucesso!");}
    }

    private void cadastrarPrato() {
        try {
            System.out.println("=== CADASTRO DE PRATOS ===");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Preço: ");
            double preco = Double.parseDouble(scanner.nextLine());
            Prato prato = new Prato(nome, preco);
            Cardapio.put(prato.getId(), prato);

            System.out.println("Prato cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("Por favor, insira um valor numérico válido!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar Prato: " + e.getMessage());
        }
    }

    private void listarPratos() {
        System.out.println("=== CARDAPIO ===");
        if (Cardapio.isEmpty()) {
            System.out.println("Nenhum prato cadastrado.");
            return;
        }

        for (Prato prato : Cardapio.values()) {
            System.out.println("ID: " + prato.getId());
            System.out.println("Nome: " + prato.getNome());
            System.out.println("Preço: R$" + prato.getPreco());
            System.out.println("------------------------");
        }
    }


    private void aplicarDescontoProduto() {

        System.out.println("=== APLICAR DESCONTO EM PRODUTO ===");
        if (Cardapio.isEmpty()) {
            System.out.println("O cardápio está vazio. Cadastre pratos antes de aplicar descontos.");
            return;
        }

        listarPratos();

        System.out.print("Digite o nome do prato para aplicar o desconto: ");
        String nomePrato = scanner.nextLine();

         Prato pratoSelecionado = null;

        for (Prato prato : Cardapio.values()) {
            if (prato.getNome().equalsIgnoreCase(nomePrato)) {
                pratoSelecionado = prato;
                break;
            }
        }

        if (pratoSelecionado == null) {
            System.out.println("Prato não encontrado no cardápio!");
            return;
        }

        System.out.print("Digite o percentual de desconto ");
        try {
            double percentualDesconto = Double.parseDouble(scanner.nextLine());
            if (percentualDesconto < 0 || percentualDesconto > 100) {
                System.out.println("O desconto deve ser entre 0% e 100%.");
            } else {
                pratoSelecionado.aplicarDesconto(percentualDesconto);
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, insira um número válido.");
        }
    }
    public void adicionarEntrega(Entrega entrega) {
        listaEntregas.add(entrega);
    }

    public void exibirERealizarEntregas() {
        for (Entrega entrega : listaEntregas) {
            entrega.realizarEntrega();
        }
    }

    private void adicionarServicoEntrega() {
        System.out.println("=== CADASTRO DE SERVIÇO DE ENTREGA ===");
        System.out.print("Digite o preço da entrega: ");
        double precoEntrega = Double.parseDouble(scanner.nextLine());


        entregaComum entrega = new entregaComum("", precoEntrega);

        entrega.pedirEndereco();


        adicionarEntrega(entrega);
        System.out.println("Serviço de entrega adicionado com sucesso!");
    }
}




