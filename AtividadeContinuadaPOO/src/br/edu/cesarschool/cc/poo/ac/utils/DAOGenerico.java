package br.edu.cesarschool.cc.poo.ac.utils;

import java.io.Serializable;
import br.edu.cesarschool.next.oo.persistenciaobjetos.*;

public class DAOGenerico<T extends Registro> {
    private CadastroObjetos cadastro;
    private Class<T> tipo;

    public DAOGenerico(Class<T> tipo) {
        this.tipo = tipo;
        this.cadastro = new CadastroObjetos(tipo);
    }

    public boolean incluir(T reg) {
        String idUnico = reg.getIdUnico();
        if (buscar(idUnico) == null) {
            cadastro.incluir(reg, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(T reg) {
        String idUnico = reg.getIdUnico();
        if (buscar(idUnico) != null) {
            cadastro.alterar(reg, idUnico);
            return true;
        }
        return false;
    }
    
    public T buscar(String id) {
        Serializable resultado = cadastro.buscar(id);
        if (tipo.isInstance(resultado)) {
            return tipo.cast(resultado);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public T[] buscarTodos() {
        Serializable[] resultados = cadastro.buscarTodos();
        T[] array = (T[]) java.lang.reflect.Array.newInstance(tipo, resultados.length);
        for (int i = 0; i < resultados.length; i++) {
            array[i] = tipo.cast(resultados[i]);
        }
        return array;
    }


    public boolean excluir(String id) {
        if (buscar(id) != null) {
            cadastro.excluir(id);
            return true;
        }
        return false;
    }
}
