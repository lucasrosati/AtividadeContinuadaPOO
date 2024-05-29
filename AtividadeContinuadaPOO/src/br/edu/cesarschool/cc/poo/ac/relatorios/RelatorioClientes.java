package br.edu.cesarschool.cc.poo.ac.relatorios;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;

public class RelatorioClientes {

    public static void gerarRelatorioClientes() {
        ClienteMediator clienteMediator = ClienteMediator.obterInstancia();
        Cliente[] clientes = clienteMediator.obterClientesPorNome();

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
}
