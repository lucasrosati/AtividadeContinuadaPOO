package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.cc.poo.ac.utils.Registro;
import java.io.Serializable;

    public class Cliente extends Registro implements Serializable {
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
        if(valor > 0) {
            this.saldoPontos += valor;
        }
    }

    public void debitarPontos(double valor) {
        if(valor > 0 && this.saldoPontos >= valor) {
            this.saldoPontos -= valor;
        }
    }
}
