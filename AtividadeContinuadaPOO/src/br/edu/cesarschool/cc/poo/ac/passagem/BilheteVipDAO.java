package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class BilheteVipDAO extends SuperDAO {

    @Override
    protected Class<?> obterTipo() {
        return BilheteVip.class;
    }
    

    private CadastroObjetos cadastro = new CadastroObjetos(BilheteVip.class);

    private String obterIdUnico(BilheteVip bilheteVip) {
        return bilheteVip.gerarNumero();
    }

    public BilheteVip buscar(String numeroBilhete) {
        return (BilheteVip) cadastro.buscar(numeroBilhete);
    }

    public boolean incluir(BilheteVip bilheteVip) {
        String idUnico = obterIdUnico(bilheteVip);
        BilheteVip bilheteVipEncontrado = buscar(idUnico);
        if (bilheteVipEncontrado == null) {
            cadastro.incluir(bilheteVip, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(BilheteVip bilheteVip) {
        String idUnico = obterIdUnico(bilheteVip);
        BilheteVip bilheteVipEncontrado = buscar(idUnico);
        if (bilheteVipEncontrado != null) {
            cadastro.alterar(bilheteVip, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String numeroBilhete) {
        BilheteVip bilheteVip = buscar(numeroBilhete);
        if (bilheteVip != null) {
            cadastro.excluir(numeroBilhete);
            return true;
        }
        return false;
    }
}
