package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;

public class BilheteVipDAO extends SuperDAO<BilheteVip> {

    @Override
    public Class<BilheteVip> obterTipo() {
        return BilheteVip.class;
    }

    public boolean incluir(BilheteVip bilheteVip) {
        return daoGenerico.incluir(bilheteVip);
    }

    public boolean alterar(BilheteVip bilheteVip) {
        return daoGenerico.alterar(bilheteVip);
    }

    public BilheteVip buscar(String numeroBilhete) {
        return daoGenerico.buscar(numeroBilhete);
    }

    public BilheteVip[] buscarTodos() {
        return daoGenerico.buscarTodos();
    }

    public boolean excluir(String numeroBilhete) {
        return daoGenerico.excluir(numeroBilhete);
    }
}
