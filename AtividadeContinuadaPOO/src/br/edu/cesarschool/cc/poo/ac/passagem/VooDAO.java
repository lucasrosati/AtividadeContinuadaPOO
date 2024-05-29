package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.cc.poo.ac.utils.DAOGenerico;

public class VooDAO extends SuperDAO<Voo> {
    private DAOGenerico<Voo> daoGenerico;

    public VooDAO() {
        this.daoGenerico = new DAOGenerico<>(Voo.class);
    }

    @Override
    public Class<Voo> obterTipo() {
        return Voo.class;
    }

    public boolean incluir(Voo voo) {
        return daoGenerico.incluir(voo);
    }

    public boolean alterar(Voo voo) {
        return daoGenerico.alterar(voo);
    }

    public Voo buscar(String numeroVoo) {
        return daoGenerico.buscar(numeroVoo);
    }

    public Voo[] buscarTodos() {
        return daoGenerico.buscarTodos();
    }

    public boolean excluir(String numeroVoo) {
        return daoGenerico.excluir(numeroVoo);
    }
}
