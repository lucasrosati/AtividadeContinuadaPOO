package br.edu.cesarschool.cc.poo.ac.cliente.comexcecao;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;

public class ClienteMediator {
    private static ClienteMediator instancia;
    private ClienteDAO clienteDao = new ClienteDAO();

    private ClienteMediator() {}

    public static ClienteMediator obterInstancia() {
        if (instancia == null) {
            instancia = new ClienteMediator();
        }
        return instancia;
    }

    public void validar(Cliente cliente) throws ExcecaoValidacao {
        ExcecaoValidacao excecao = new ExcecaoValidacao();
        if (!ValidadorCPF.isCpfValido(cliente.getCpf())) {
            excecao.adicionarMensagem("CPF errado");
        }
        if (StringUtils.isVaziaOuNula(cliente.getNome()) || cliente.getNome().length() < 2) {
            excecao.adicionarMensagem("nome errado");
        }
        if (cliente.getSaldoPontos() < 0) {
            excecao.adicionarMensagem("saldo errado");
        }
        if (!excecao.getMensagens().isEmpty()) {
            throw excecao;
        }
    }

    public Cliente buscar(String cpf) throws ExcecaoRegistroInexistente {
        return clienteDao.buscar(cpf);
    }

    public void incluir(Cliente cliente) throws ExcecaoRegistroJaExistente, ExcecaoValidacao {
        validar(cliente);
        clienteDao.incluir(cliente);
    }

    public void alterar(Cliente cliente) throws ExcecaoRegistroInexistente, ExcecaoValidacao {
        validar(cliente);
        clienteDao.alterar(cliente);
    }

    public void excluir(String cpf) throws ExcecaoRegistroInexistente, ExcecaoValidacao {
        if (!ValidadorCPF.isCpfValido(cpf)) {
            ExcecaoValidacao excecao = new ExcecaoValidacao();
            excecao.adicionarMensagem("CPF errado");
            throw excecao;
        }
        clienteDao.excluir(cpf);
    }
}
