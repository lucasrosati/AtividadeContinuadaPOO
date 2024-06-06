package br.edu.cesarschool.cc.poo.ac.utils;

import java.io.Serializable;
import java.lang.reflect.Array;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

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

    public T[] buscarTodos() {
        Serializable[] resultados = cadastro.buscarTodos();
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(tipo, resultados.length);
        for (int i = 0; i < resultados.length; i++) {
            if (tipo.isInstance(resultados[i])) {
                array[i] = tipo.cast(resultados[i]);
            } else {
                // Handle the case where the cast is not valid, e.g., logging or throwing an exception
                throw new IllegalStateException("Unexpected instance type in resultados array");
            }
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
