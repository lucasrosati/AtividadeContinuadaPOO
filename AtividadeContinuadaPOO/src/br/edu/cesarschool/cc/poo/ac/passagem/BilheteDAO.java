package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class BilheteDAO extends SuperDAO {

    @Override
    protected Class<?> obterTipo() {
        return Bilhete.class;
    }
    
    private CadastroObjetos cadastro = new CadastroObjetos(Bilhete.class);

    private String obterIdUnico(Bilhete bilhete) {
        return bilhete.gerarNumero();
    }

    public Bilhete buscar(String numeroBilhete) {
        return (Bilhete) cadastro.buscar(numeroBilhete);
    }

    public boolean incluir(Bilhete bilhete) {
        String idUnico = obterIdUnico(bilhete);
        Bilhete bilheteEncontrado = buscar(idUnico);
        if (bilheteEncontrado == null) {
            cadastro.incluir(bilhete, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(Bilhete bilhete) {
        String idUnico = obterIdUnico(bilhete);
        Bilhete bilheteEncontrado = buscar(idUnico);
        if (bilheteEncontrado != null) {
            cadastro.alterar(bilhete, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String numeroBilhete) {
        Bilhete bilhete = buscar(numeroBilhete);
        if (bilhete != null) {
            cadastro.excluir(numeroBilhete);
            return true;
        }
        return false;
    }
}
