package br.edu.cesarschool.cc.poo.ac.utils;

public abstract class SuperDAO<T extends Registro> {
    protected DAOGenerico<T> daoGenerico;

    protected SuperDAO() {
        this.daoGenerico = new DAOGenerico<>(obterTipo());
    }

    public abstract Class<T> obterTipo();
}
