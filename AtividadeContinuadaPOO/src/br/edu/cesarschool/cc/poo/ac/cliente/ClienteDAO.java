package br.edu.cesarschool.cc.poo.ac.cliente;

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

    public boolean incluir(Cliente cliente) {
        return daoGenerico.incluir(cliente);
    }

    public boolean alterar(Cliente cliente) {
        return daoGenerico.alterar(cliente);
    }

    public Cliente buscar(String cpf) {
        return daoGenerico.buscar(cpf);
    }

    public Cliente[] buscarTodos() {
        return daoGenerico.buscarTodos();
    }

    public boolean excluir(String cpf) {
        return daoGenerico.excluir(cpf);
    }
}
