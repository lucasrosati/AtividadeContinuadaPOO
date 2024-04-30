package br.edu.cesarschool.cc.poo.ac.utils;

import java.util.HashMap;
import java.util.Map;

public enum DiaDaSemana {
    SEGUNDA_FEIRA(1, "segunda-feira"),
    TERCA_FEIRA(2, "terça-feira"),
    QUARTA_FEIRA(3, "quarta-feira"),
    QUINTA_FEIRA(4, "quinta-feira"),
    SEXTA_FEIRA(5, "sexta-feira"),
    SABADO(6, "sábado"),
    DOMINGO(7, "domingo");

    private final int codigo;
    private final String nome;

    private static final Map<Integer, DiaDaSemana> codigoMap = new HashMap<>();

    static {
        for (DiaDaSemana dia : DiaDaSemana.values()) {
            codigoMap.put(dia.codigo, dia);
        }
    }

    DiaDaSemana(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public static DiaDaSemana getDiaDaSemana(int codigo) {
        return codigoMap.get(codigo);
    }
}
