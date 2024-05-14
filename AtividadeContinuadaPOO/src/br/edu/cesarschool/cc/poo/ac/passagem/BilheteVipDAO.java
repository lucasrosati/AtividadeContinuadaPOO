package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.cc.poo.ac.utils.DAOGenerico;

public class BilheteVipDAO extends SuperDAO<BilheteVip> {
    private DAOGenerico<BilheteVip> daoGenerico;

    public BilheteVipDAO() {
        this.daoGenerico = new DAOGenerico<>(BilheteVip.class);
    }

    @Override
    protected Class<BilheteVip> obterTipo() {
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
