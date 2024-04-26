package br.edu.cesarschool.cc.poo.ac.passagem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TelaVoo {

    private static final String DIGITE_O_ID = "Digite o ID: ";
    private static final String VOO_NAO_ENCONTRADO = "Voo não encontrado!";
    private static final String ID_DESCONHECIDO = "-1";
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final BufferedReader ENTRADA_STR = new BufferedReader(new InputStreamReader(System.in));
    private VooMediator vooMediator = VooMediator.obterInstancia();

    public void inicializaTelasCadastroVoo() {
        while(true) {
            String id = ID_DESCONHECIDO;            
            imprimeMenuPrincipal();
            int opcao = ENTRADA.nextInt();
            if (opcao == 1) {                
                processaInclusao();
            } else if (opcao == 2) {
                id = processaBusca();
                if (!id.equals(ID_DESCONHECIDO)) {
                    processaAlteracao(id);
                } 
            } else if (opcao == 3) {
                id = processaBusca();
                if (!id.equals(ID_DESCONHECIDO)) {
                    processaExclusao(id);
                }           
            } else if (opcao == 4) {
                processaBusca();
            } else if (opcao == 5) {
                System.out.println("Saindo do cadastro de voos");
                System.exit(0);
            } else {
                System.out.println("Opção inválida!!");
            }
        } 
    }
    
    private void imprimeMenuPrincipal() {        
        System.out.println("1- Incluir");
        System.out.println("2- Alterar");
        System.out.println("3- Excluir");
        System.out.println("4- Buscar");
        System.out.println("5- Sair");
        System.out.print("Digite a opção: ");
    }
        
    private void processaInclusao() {
        Voo voo = capturaVoo();
        String retorno = vooMediator.incluir(voo);
        if (retorno == null) { 
            System.out.println("Voo incluído com sucesso!");
        } else {
            System.out.println(retorno);            
        }
    }
    
    private void processaAlteracao(String id) {
        Voo voo = capturaVoo();
        String retorno = vooMediator.alterar(voo);
        if (retorno == null) { 
            System.out.println("Voo alterado com sucesso!");
        } else {
            System.out.println(retorno);        
        }
    }
    
    private String processaBusca() {
        System.out.print(DIGITE_O_ID);
        String id = ENTRADA.next();
        Voo voo = vooMediator.buscar(id);
        if (voo == null) {
            System.out.println(VOO_NAO_ENCONTRADO);
            return ID_DESCONHECIDO;
        } else {
            System.out.println("ID: " + id);
            System.out.println("Origem: " + voo.getAeroportoOrigem());
            System.out.println("Destino: " + voo.getAeroportoDestino());
            return id;
        }
    }
    
    private void processaExclusao(String id) {
        String retorno = vooMediator.excluir(id);
        if (retorno == null) {
            System.out.println("Voo excluído com sucesso!");
        } else {
            System.out.println(retorno);
        }
    }
    
    private Voo capturaVoo() {
        System.out.print("Digite a origem: ");
        String origem = lerString();
        System.out.print("Digite o destino: ");
        String destino = lerString();
        System.out.print("Digite a companhia aérea: ");
        String companhia = lerString();
        System.out.print("Digite o número do voo: ");
        int numeroVoo = ENTRADA.nextInt();
        
        return new Voo(origem, destino, companhia, numeroVoo);
    }

    private String lerString() {
        try {            
            return ENTRADA_STR.readLine();            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
