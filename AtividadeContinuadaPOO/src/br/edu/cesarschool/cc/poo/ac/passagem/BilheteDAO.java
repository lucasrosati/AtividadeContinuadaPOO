package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;

public class BilheteDAO extends SuperDAO<Bilhete> {

    @Override
    public Class<Bilhete> obterTipo() {
        return Bilhete.class;
    }

    public boolean incluir(Bilhete bilhete) {
        return daoGenerico.incluir(bilhete);
    }

    public boolean alterar(Bilhete bilhete) {
        return daoGenerico.alterar(bilhete);
    }

    public Bilhete buscar(String numeroBilhete) {
        return daoGenerico.buscar(numeroBilhete);
    }

    public Bilhete[] buscarTodos() {
        return daoGenerico.buscarTodos();
    }

    public boolean excluir(String numeroBilhete) {
        return daoGenerico.excluir(numeroBilhete);
    }
}
