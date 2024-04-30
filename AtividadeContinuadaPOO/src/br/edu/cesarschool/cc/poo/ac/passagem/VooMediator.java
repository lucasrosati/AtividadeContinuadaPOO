package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.DiaDaSemana;
import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;
import java.util.HashSet;
import java.util.Set;

public class VooMediator {
    private static VooMediator instancia;
    private VooDAO vooDao = new VooDAO(); 
    private final Set<String> aeroportosValidos;

    private VooMediator() {
        aeroportosValidos = new HashSet<>();
        String[] aeroportos = {"GRU", "CGH", "GIG", "SDU", "REC", "CWB", "POA", "BSB", "SSA", "FOR", "MAO",
                "SLZ", "CNF", "BEL", "JPA", "PNZ", "CAU", "FEN", "SET", "NAT", "PVH", "BVB", "FLN", "AJU",
                "PMW", "MCZ", "MCP", "VIX", "GYN", "CGB", "CGR", "THE", "RBR", "VCP", "RAO"};
        for (String aeroporto : aeroportos) {
            aeroportosValidos.add(aeroporto);
        }
    }

    public static synchronized VooMediator obterInstancia() {
        if (instancia == null) {
            instancia = new VooMediator();
        }
        return instancia;
    }

    public Voo buscar(String idVoo) {
        return vooDao.buscar(idVoo);
    }

    public String validar(Voo voo) {
        if (StringUtils.isVaziaOuNula(voo.getAeroportoOrigem())) {
            return "Aeroporto origem errado";
        }
        if (StringUtils.isVaziaOuNula(voo.getAeroportoDestino())) {
            return "Aeroporto destino errado";
        }
        if (!aeroportosValidos.contains(voo.getAeroportoOrigem())) {
            return "Aeroporto origem errado";
        }
        if (!aeroportosValidos.contains(voo.getAeroportoDestino())) {
            return "Aeroporto destino errado";
        }
        if (voo.getAeroportoOrigem().equals(voo.getAeroportoDestino())) {
            return "Aeroporto origem igual a aeroporto destino";
        }
        String erroCiaNumero = validarCiaNumero(voo.getCompanhiaAerea(), voo.getNumeroVoo());
        if (erroCiaNumero != null) {
            return erroCiaNumero;
        }
        if (voo.getDiasDaSemana() == null || voo.getDiasDaSemana().length == 0) {
            return "Dias da semana nao informados";
        }
        Set<DiaDaSemana> diasSet = new HashSet<>();
        for (DiaDaSemana dia : voo.getDiasDaSemana()) {
            if (!diasSet.add(dia)) {
                return "Dia da semana repetido";
            }
        }
        if (voo.getHora() == null) {
            return "Hora nao informada";
        }
        if (voo.getHora().getSecond() != 0 || voo.getHora().getNano() != 0) {
            return "Hora invalida";
        }
        return null;
    }

    public String validarCiaNumero(String companhiaAerea, int numeroVoo) {
        if (StringUtils.isVaziaOuNula(companhiaAerea) || companhiaAerea.length() != 2) {
            return "CIA aerea errada";
        }
        if (numeroVoo <= 0 || numeroVoo < 1000 || numeroVoo > 9999) {
            return "Numero voo errado";
        }
        return null;
    }

    public String incluir(Voo voo) {
        String erroValidacao = validar(voo);
        if (erroValidacao != null) {
            return erroValidacao;
        }
        boolean sucesso = vooDao.incluir(voo);
        return sucesso ? null : "Voo ja existente";
    }

    public String alterar(Voo voo) {
        String erroValidacao = validar(voo);
        if (erroValidacao != null) {
            return erroValidacao;
        }
        boolean sucesso = vooDao.alterar(voo);
        return sucesso ? null : "Voo inexistente";
    }

    public String excluir(String idVoo) {
        if (StringUtils.isVaziaOuNula(idVoo)) {
            return "Id voo errado";
        }
        boolean sucesso = vooDao.excluir(idVoo);
        return sucesso ? null : "Voo inexistente";
    }

    public String obterIdVoo(Voo voo) {
        return voo.obterIdVoo();
    }
}
