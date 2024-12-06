@startuml
'https://plantuml.com/class-diagram

abstract class Produto {
    - static int contadorIds = 1
    - int id
    - String nome
    - double preco

    + Produto(String nome, double preco)
    + int getId()
    + String getNome()
    + double getPreco()
    + void aplicarDesconto(double porcentagem)
    + String toString()
}

Produto ..|> Promocao
interface Promocao {
    + void aplicarDesconto(double porcentagem)
}

abstract class Usuario {
    # String nome
    # String email

    + Usuario(String nome, String email)
    + String getNome()
    + String getEmail()
    + String toString()
}

class Prato {
    + Prato(String nome, double preco)
    + double calcularPreco()
}

Prato --> Produto : extends
Prato ..> Compravel : implements

class Gerente {
    + Gerente(String nome, String email)
    + String toString()
}

Gerente --> Usuario : extends

class Funcionario {
    - String cargo
    + Funcionario(String nome, String email, String cargo)
    + String toString()
}

Funcionario --> Usuario : extends

class entregaComum {
    + entregaComum(String enderecoCliente, double precoEntrega)
    + void realizarEntrega()
}

entregaComum --> Delivery : extends
interface Entrega {
    + void pedirEndereco()
    + void realizarEntrega();

}

abstract class Delivery {
    - String enderecoCliente
    - double precoEntrega
    + Delivery(String enderecoCliente, double precoEntrega)
    + void pedirEndereco()
    + String getEnderecoCliente()
    + void setEnderecoCliente(String enderecoCliente)
    + double getPrecoEntrega()
    + void setPrecoEntrega(double precoEntrega)
}

Delivery --> Entrega : implements

interface Compravel {
    + double calcularPreco()
}

interface Promocao {
    + void aplicarDesconto(double porcentagem)
}

class SistemaDoRestaurante {
    - Scanner scanner
    - Map<String, Usuario> usuarios
    - List<Entrega> listaEntregas
    - Map<String, Funcionario> funcionarios
    - Usuario usuarioLogado
    - Map<Integer, Prato> Cardapio

    + SistemaDoRestaurante()
    + void iniciar()
    - void menuLogin() throws LoginInvalido
    - void realizarLogin(int tipoUsuario, String email) throws LoginInvalido
    - void exibirMenuPrincipal(int tipoUsuario)
    - boolean menuGerente()
    - boolean menuFuncionario()
    - void cadastrarFuncionario() throws PermissaoNegada
    - void cadastrarPrato()
    - void listarPratos()
    - void aplicarDescontoProduto()
    + void adicionarEntrega(Entrega entrega)
    + void exibirERealizarEntregas()
    - void adicionarServicoEntrega()
}

SistemaDoRestaurante --> Usuario : gerencia
SistemaDoRestaurante --  Prato : gerencia
SistemaDoRestaurante --> Entrega : gerencia
SistemaDoRestaurante ..> Gerente
SistemaDoRestaurante ..> Funcionario
SistemaDoRestaurante ..> entregaComum
SistemaDoRestaurante ..> Delivery

class Usuario {
    {abstract}
}
class Gerente
class Funcionario

class Entrega {
    {interface}
}
class entregaComum {
    {implements}
}
class Delivery {
    {abstract}
}
class Main {
    + void main(String[] args)
}

class SistemaDoRestaurante {
    + void iniciar()
}

Main --> SistemaDoRestaurante : "Inicializa"

package Exceptions {
    class RuntimeException {
    }

    class LoginInvalido {
        + LoginInvalido(String message)
    }

    LoginInvalido --> RuntimeException : "extends"
}

package Exceptions {
    class Exception {
    }

    class PermissaoNegada {
        - mensagem: String
        + PermissaoNegada(String mensagem)
    }

    PermissaoNegada --> Exception : "extends"
}

@enduml





