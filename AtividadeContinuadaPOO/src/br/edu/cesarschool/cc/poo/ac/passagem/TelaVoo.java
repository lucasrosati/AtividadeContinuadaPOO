package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.DiaDaSemana;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelaVoo {

    private static final String DIGITE_O_ID = "Digite o ID: ";
    private static final String VOO_NAO_ENCONTRADO = "Voo não encontrado!";
    private static final String ID_DESCONHECIDO = "-1";
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final BufferedReader ENTRADA_STR = new BufferedReader(new InputStreamReader(System.in));
    private VooMediator vooMediator = VooMediator.obterInstancia();

    public void inicializaTelasCadastroVoo() {
        while (true) {
            imprimeMenuPrincipal();
            int opcao = ENTRADA.nextInt();
            switch (opcao) {
                case 1:
                    processaInclusao();
                    break;
                case 2:
                    String id = processaBusca();
                    if (!id.equals(ID_DESCONHECIDO)) {
                        processaAlteracao(id);
                    }
                    break;
                case 3:
                    id = processaBusca();
                    if (!id.equals(ID_DESCONHECIDO)) {
                        processaExclusao(id);
                    }
                    break;
                case 4:
                    processaBusca();
                    break;
                case 5:
                    System.out.println("Saindo do cadastro de voos");
                    System.exit(0);
                    break;
                default:
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
            System.out.println("Dias da semana: " + formatarDiasDaSemana(voo.getDiasDaSemana()));
            System.out.println("Hora: " + voo.getHora().toString());
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

        DiaDaSemana[] diasDaSemana = capturarDiasDaSemana();
        LocalTime hora = capturarHora();

        return new Voo(origem, destino, companhia, numeroVoo, diasDaSemana, hora);
    }

    private DiaDaSemana[] capturarDiasDaSemana() {
        System.out.println("Digite os dias da semana (números de 1 a 7, separados por espaço):");
        String[] diasInput = lerString().split(" ");
        List<DiaDaSemana> dias = new ArrayList<>();
        for (String dia : diasInput) {
            dias.add(DiaDaSemana.getDiaDaSemana(Integer.parseInt(dia)));
        }
        return dias.toArray(new DiaDaSemana[0]);
    }

    private LocalTime capturarHora() {
        System.out.print("Digite a hora (HH MM): ");
        int hora = ENTRADA.nextInt();
        int minuto = ENTRADA.nextInt();
        return LocalTime.of(hora, minuto, 0, 0);
    }

    private String formatarDiasDaSemana(DiaDaSemana[] dias) {
        StringBuilder sb = new StringBuilder();
        for (DiaDaSemana dia : dias) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(dia.getNome());
        }
        return sb.toString();
    }

    private String lerString() {
        try {
            return ENTRADA_STR.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
