package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class FormaPagamentoDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(FormaPagamento.class);

    private String obterIdUnico(FormaPagamento formaPagamento) {
        return String.valueOf(formaPagamento.getCodigo());
    }

    public FormaPagamento buscar(int codigo) {
        return (FormaPagamento) cadastro.buscar(String.valueOf(codigo));
    }

    public boolean incluir(FormaPagamento formaPagamento) {
        String idUnico = obterIdUnico(formaPagamento);
        FormaPagamento fp = buscar(Integer.parseInt(idUnico));
        if (fp == null) {
            cadastro.incluir(formaPagamento, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(FormaPagamento formaPagamento) {
        String idUnico = obterIdUnico(formaPagamento);
        FormaPagamento fp = buscar(Integer.parseInt(idUnico));
        if (fp != null) {
            cadastro.alterar(formaPagamento, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(int codigo) {
        FormaPagamento fp = buscar(codigo);
        if (fp != null) {
            cadastro.excluir(String.valueOf(codigo));
            return true;
        }
        return false;
    }
}
