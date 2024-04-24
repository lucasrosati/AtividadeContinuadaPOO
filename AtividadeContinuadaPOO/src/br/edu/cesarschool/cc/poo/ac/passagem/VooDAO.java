package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class VooDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(Voo.class);

    private String obterIdUnico(Voo voo) {
        return voo.getCompanhiaAerea() + voo.getNumeroVoo();
    }

    public Voo buscar(String idVoo) {
        return (Voo) cadastro.buscar(idVoo);
    }

    public boolean incluir(Voo voo) {
        String idUnico = obterIdUnico(voo);
        Voo vooEncontrado = buscar(idUnico);
        if (vooEncontrado == null) {
            cadastro.incluir(voo, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(Voo voo) {
        String idUnico = obterIdUnico(voo);
        Voo vooEncontrado = buscar(idUnico);
        if (vooEncontrado != null) {
            cadastro.alterar(voo, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String idVoo) {
        Voo voo = buscar(idVoo);
        if (voo != null) {
            cadastro.excluir(idVoo);
            return true;
        }
        return false;
    }
}
