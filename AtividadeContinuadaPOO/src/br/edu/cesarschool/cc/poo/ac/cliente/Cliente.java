package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.cc.poo.ac.utils.Registro;
import br.edu.cesarschool.cc.poo.ac.utils.ordenacao.Comparavel;

import java.io.Serializable;

public class Cliente extends Registro implements Serializable, Comparavel {
    private static final long serialVersionUID = 1L;
    private String cpf;
    private String nome;
    private double saldoPontos;

    public Cliente(String cpf, String nome, double saldoPontos) {
        this.cpf = cpf;
        this.nome = nome;
        this.saldoPontos = saldoPontos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldoPontos() {
        return saldoPontos;
    }

    public void setSaldoPontos(double saldoPontos) {
        this.saldoPontos = saldoPontos;
    }

    public void creditarPontos(double valor) {
        if (valor > 0) {
            this.saldoPontos += valor;
        }
    }

    public void debitarPontos(double valor) {
        if (valor > 0 && this.saldoPontos >= valor) {
            this.saldoPontos -= valor;
        }
    }

    @Override
    public String getIdUnico() {
        return cpf;
    }

    @Override
    public int comparar(Object o1) {
        if (o1 instanceof Cliente) {
            Cliente outro = (Cliente) o1;
            return this.nome.compareTo(outro.nome);
        }
        return 0;
    }

    @Override
    public String toString() {
        return nome + " , " + cpf + " , " + saldoPontos;
    }
}
