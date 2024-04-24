package br.edu.cesarschool.cc.poo.ac.cliente;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TelaCliente {
    private ClienteMediator clienteMediator = ClienteMediator.obterInstancia();
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final BufferedReader ENTRADA_STR = new BufferedReader(new InputStreamReader(System.in));

    public void inicializaTelaCadastroCliente() {
        while (true) {
            imprimeMenuPrincipal();
            int opcao = ENTRADA.nextInt();
            ENTRADA.nextLine();

            switch (opcao) {
                case 1:
                    processaInclusao();
                    break;
                case 2:
                    processaAlteracao();
                    break;
                case 3:
                    processaExclusao();
                    break;
                case 4:
                    processaBusca();
                    break;
                case 5:
                    System.out.println("Saindo do cadastro de clientes");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void imprimeMenuPrincipal() {
        System.out.println("1- Incluir Cliente");
        System.out.println("2- Alterar Cliente");
        System.out.println("3- Excluir Cliente");
        System.out.println("4- Buscar Cliente");
        System.out.println("5- Sair");
        System.out.print("Digite a opção: ");
    }

    private void processaInclusao() {
        Cliente cliente = capturaCliente();
        String retorno = clienteMediator.incluir(cliente);
        if (retorno == null) {
            System.out.println("Cliente incluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private void processaAlteracao() {
        System.out.print("Digite o CPF do cliente para alteração: ");
        String cpf = ENTRADA.nextLine();
        Cliente clienteExistente = clienteMediator.buscar(cpf);
        if (clienteExistente == null) {
            System.out.println("Cliente não encontrado!");
        } else {
            System.out.println("Atualize as informações do cliente:");
            Cliente clienteAtualizado = capturaCliente();
            clienteAtualizado.setCPF(cpf);
            String retorno = clienteMediator.alterar(clienteAtualizado);
            if (retorno == null) {
                System.out.println("Cliente alterado com sucesso!");
            } else {
                System.out.println(retorno);
            }
        }
    }

    private void processaExclusao() {
        System.out.print("Digite o CPF do cliente para exclusão: ");
        String cpf = ENTRADA.nextLine();
        String retorno = clienteMediator.excluir(cpf);
        if (retorno == null) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }

    private void processaBusca() {
        System.out.print("Digite o CPF do cliente para busca: ");
        String cpf = ENTRADA.nextLine();
        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
        } else {
            exibirInformacoesCliente(cliente);
        }
    }

    private void exibirInformacoesCliente(Cliente cliente) {
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Saldo de Pontos: " + cliente.getSaldoPontos());
    }

    private Cliente capturaCliente() {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = lerString();
        System.out.print("Digite o nome do cliente: ");
        String nome = lerString();
        System.out.print("Digite o saldo de pontos do cliente: ");
        double saldoPontos = ENTRADA.nextDouble();
        ENTRADA.nextLine();
        return new Cliente(cpf, nome, saldoPontos);
    }

    private String lerString() {
        try {
            return ENTRADA_STR.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
