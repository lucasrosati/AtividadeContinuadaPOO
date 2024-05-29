package br.edu.cesarschool.cc.poo.ac.cliente.comexcecao;

import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.cc.poo.ac.utils.DAOGenerico;

public class ClienteDAO extends SuperDAO<Cliente> {
    private DAOGenerico<Cliente> daoGenerico;

    public ClienteDAO() {
        this.daoGenerico = new DAOGenerico<>(Cliente.class);
    }

    @Override
    public Class<Cliente> obterTipo() {
        return Cliente.class;
    }

    public Cliente buscar(String cpf) throws ExcecaoRegistroInexistente {
        Cliente cliente = daoGenerico.buscar(cpf);
        if (cliente == null) {
            throw new ExcecaoRegistroInexistente("Cliente inexistente");
        }
        return cliente;
    }

    public void incluir(Cliente cliente) throws ExcecaoRegistroJaExistente {
        if (daoGenerico.buscar(cliente.getCpf()) != null) {
            throw new ExcecaoRegistroJaExistente("Cliente existente");
        }
        daoGenerico.incluir(cliente);
    }

    public void alterar(Cliente cliente) throws ExcecaoRegistroInexistente {
        if (daoGenerico.buscar(cliente.getCpf()) == null) {
            throw new ExcecaoRegistroInexistente("Cliente inexistente");
        }
        daoGenerico.alterar(cliente);
    }

    public void excluir(String cpf) throws ExcecaoRegistroInexistente {
        if (daoGenerico.buscar(cpf) == null) {
            throw new ExcecaoRegistroInexistente("Cliente inexistente");
        }
        daoGenerico.excluir(cpf);
    }
}
