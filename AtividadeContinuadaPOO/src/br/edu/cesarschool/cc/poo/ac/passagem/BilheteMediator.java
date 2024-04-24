package br.edu.cesarschool.cc.poo.ac.passagem;

import java.time.LocalDateTime;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.cliente.ClienteMediator;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;

public class BilheteMediator {
    private BilheteDAO bilheteDao;
    private BilheteVipDAO bilheteVipDao;
    private VooMediator vooMediator;
    private ClienteMediator clienteMediator;

    private static BilheteMediator instancia;
   
    private BilheteMediator() {
    	this.vooMediator = VooMediator.obterInstancia();
    	this.clienteMediator = ClienteMediator.obterInstancia();
    	this.bilheteDao = new BilheteDAO();
    	this.bilheteVipDao = new BilheteVipDAO();
    }

    public static BilheteMediator obterInstancia() {
        if (instancia == null) {
            instancia = new BilheteMediator();
        }
        return instancia;
    }

   
    public Bilhete buscar(String numeroBilhete) {
        return bilheteDao.buscar(numeroBilhete);
    }

    public BilheteVip buscarVip(String numeroBilhete) {
        return (BilheteVip) bilheteVipDao.buscar(numeroBilhete);
    }

    public String validar(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
        
        if (!ValidadorCPF.isCpfValido(cpf)) {
            return "CPF errado";
        }       
        String mensagemCiaNumero = vooMediator.validarCiaNumero(ciaAerea, numeroVoo);
        if (mensagemCiaNumero != null) {
            return mensagemCiaNumero;
        }
        
        if (preco <= 0) {
            return "Preco errado";
        }
        
        if (pagamentoEmPontos < 0) {
            return "Pagamento pontos errado";
        }
       
        if (preco < pagamentoEmPontos) {
            return "Preco menor que pagamento em pontos";
        }
      
        LocalDateTime horaAtualMais1h = LocalDateTime.now().plusHours(1);
        if (dataHora.isBefore(horaAtualMais1h)) {
            return "data hora invalida";
        }

        return null;
    }

    public ResultadoGeracaoBilhete gerarBilhete(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora) {
        String mensagemValida = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
        if (mensagemValida != null) {
            return new ResultadoGeracaoBilhete(null, null, mensagemValida);
        }

        Voo voo = new Voo(null, null, ciaAerea, numeroVoo);
        String idVoo = voo.obterIdVoo();
        Voo vooEncontrado = vooMediator.buscar(idVoo);
        if (vooEncontrado == null) {
            return new ResultadoGeracaoBilhete(null, null, "Voo nao encontrado");
        }

        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            return new ResultadoGeracaoBilhete(null, null, "Cliente nao encontrado");
        }

        double pontosNecessarios = pagamentoEmPontos * 20;
        if (cliente.getSaldoPontos() < pontosNecessarios) {
            return new ResultadoGeracaoBilhete(null, null, "Pontos insuficientes");
        }

        double valorPago = preco - pagamentoEmPontos;
        double pontuacao = valorPago / 20;

        cliente.creditarPontos(pontuacao);
        cliente.debitarPontos(pontosNecessarios);

        Bilhete bilhete = new Bilhete(cliente, vooEncontrado, preco, pagamentoEmPontos, dataHora);
        boolean incluido = bilheteDao.incluir(bilhete);
        if (!incluido) {
            return new ResultadoGeracaoBilhete(null, null, "Bilhete ja existente");
        }

        String mensagemAlteracaoCliente = clienteMediator.alterar(cliente);
        if (mensagemAlteracaoCliente != null) {
            return new ResultadoGeracaoBilhete(null, null, mensagemAlteracaoCliente);
        }

        return new ResultadoGeracaoBilhete(bilhete, null, null);
    }

    public ResultadoGeracaoBilhete gerarBilheteVip(String cpf, String ciaAerea, int numeroVoo, double preco, double pagamentoEmPontos, LocalDateTime dataHora, double bonusPontuacao) {
        String validacao = validar(cpf, ciaAerea, numeroVoo, preco, pagamentoEmPontos, dataHora);
        if (validacao != null) {
            return new ResultadoGeracaoBilhete(null, null, validacao);
        }

        if (bonusPontuacao <= 0 || bonusPontuacao > 100) {
            return new ResultadoGeracaoBilhete(null, null, "Bonus errado");
        }

        Voo voo = new Voo(null, null, ciaAerea, numeroVoo);
        String idVoo = voo.obterIdVoo();
        Voo vooEncontrado = vooMediator.buscar(idVoo);

        if (vooEncontrado == null) {
            return new ResultadoGeracaoBilhete(null, null, "Voo nao encontrado");
        }

        Cliente cliente = clienteMediator.buscar(cpf);
        if (cliente == null) {
            return new ResultadoGeracaoBilhete(null, null, "Cliente nao encontrado");
        }

        double pontosNecessarios = pagamentoEmPontos * 20;
        if (cliente.getSaldoPontos() < pontosNecessarios) {
            return new ResultadoGeracaoBilhete(null, null, "Pontos insuficientes");
        }

        cliente.debitarPontos(pontosNecessarios);
        double pontuacaoVip = (preco - pagamentoEmPontos) * (1 + bonusPontuacao / 100) / 20;
        cliente.creditarPontos(pontuacaoVip);

        BilheteVip bilheteVip = new BilheteVip(cliente, vooEncontrado, preco, pagamentoEmPontos, dataHora, bonusPontuacao);
        boolean incluso = bilheteVipDao.incluir(bilheteVip);

        if (!incluso) {
            return new ResultadoGeracaoBilhete(null, null, "Bilhete ja existente");
        }

        String mensagemAlteradaCliente = clienteMediator.alterar(cliente);
        if (mensagemAlteradaCliente != null) {
            return new ResultadoGeracaoBilhete(null, null, mensagemAlteradaCliente);
        }

        return new ResultadoGeracaoBilhete(null, bilheteVip, null);
    }
}