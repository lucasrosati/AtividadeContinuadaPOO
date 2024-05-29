package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.Registro;
import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bilhete extends Registro {
    private Cliente cliente;
    private Voo voo;
    private double preco;
    private double pagamentoEmPontos;
    private LocalDateTime dataHora;
    private LocalDateTime dhInclusao;

    public Bilhete(Cliente cliente, Voo voo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
        this.cliente = cliente;
        this.voo = voo;
        this.preco = preco;
        this.pagamentoEmPontos = pagamentoEmPontos;
        this.dataHora = dataHora;
        this.dhInclusao = LocalDateTime.now();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Voo getVoo() {
        return voo;
    }

    public double getPreco() {
        return preco;
    }

    public double getPagamentoEmPontos() {
        return pagamentoEmPontos;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public LocalDateTime getDhInclusao() {
        return dhInclusao;
    }

    public void setPagamentoEmPontos(double pagamentoEmPontos) {
        this.pagamentoEmPontos = pagamentoEmPontos;
    }

    public double obterValorPago() {
        return preco - pagamentoEmPontos;
    }

    public double obterValorPontuacao() {
        return obterValorPago() / 20;
    }

    public String gerarNumero() {
        return this.cliente.getCpf() + this.voo.getNumeroVoo() + this.dataHora.getYear() + this.dataHora.getMonthValue() + this.dataHora.getDayOfMonth();
    }

    @Override
    public String getIdUnico() {
        return gerarNumero();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("%.2f , %s , %s, %s",
                             preco,
                             voo.getAeroportoOrigem(),
                             voo.getAeroportoDestino(),
                             dataHora.format(formatter));
    }
}
