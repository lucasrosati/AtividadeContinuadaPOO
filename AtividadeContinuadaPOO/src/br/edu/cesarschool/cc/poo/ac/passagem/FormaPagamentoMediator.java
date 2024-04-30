package br.edu.cesarschool.cc.poo.ac.passagem;

public class FormaPagamentoMediator {
    private static FormaPagamentoMediator instancia;
    private FormaPagamentoDAO formaPagamentoDao = new FormaPagamentoDAO();

    private FormaPagamentoMediator() {}

    public static FormaPagamentoMediator obterInstancia() {
        if (instancia == null) {
            instancia = new FormaPagamentoMediator();
        }
        return instancia;
    }

    public FormaPagamento buscar(int codigo) {
        return formaPagamentoDao.buscar(codigo);
    }

    public String validar(FormaPagamento formaPagamento) {
        if (formaPagamento.getCodigo() <= 0 || formaPagamento.getCodigo() < 10 || formaPagamento.getCodigo() > 99) {
            return "codigo errado";
        }
        if (formaPagamento.getDescricao() == null || formaPagamento.getDescricao().length() < 2) {
            return "descricao errada";
        }
        if (formaPagamento.getValorMinimo() <= 0) {
            return "valor minimo errado";
        }
        if (formaPagamento.getValorMaximo() <= 0 || formaPagamento.getValorMaximo() < formaPagamento.getValorMinimo()) {
            return "valor maximo errado";
        }
        return null;
    }

    public String incluir(FormaPagamento formaPagamento) {
        String erro = validar(formaPagamento);
        if (erro != null) {
            return erro;
        }
        boolean sucesso = formaPagamentoDao.incluir(formaPagamento);
        return sucesso ? null : "Forma pagamento ja existente";
    }

    public String alterar(FormaPagamento formaPagamento) {
        String erro = validar(formaPagamento);
        if (erro != null) {
            return erro;
        }
        boolean sucesso = formaPagamentoDao.alterar(formaPagamento);
        return sucesso ? null : "Forma pagamento inexistente";
    }

    public String excluir(int codigo) {
        boolean sucesso = formaPagamentoDao.excluir(codigo);
        return sucesso ? null : "Forma pagamento inexistente";
    }
}
