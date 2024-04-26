package br.edu.cesarschool.cc.poo.ac.passagem;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class VooDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(Voo.class);

    private String obterIdUnico(Voo voo) {
        return voo.getCompanhiaAerea() + voo.getNumeroVoo();
    }

    public Voo buscar(String idVoo) {
        return (Voo) cadastro.buscar(idVoo);
    }

    public Voo[] buscarTodos() {
        Serializable[] res = cadastro.buscarTodos();
        if (res == null) {
            return null;
        } else {
            Voo[] voos = new Voo[res.length];
            int i = 0;
            for (Serializable reg : res) {
                voos[i] = (Voo) reg;
                i++;
            }
            return voos;
        }
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
