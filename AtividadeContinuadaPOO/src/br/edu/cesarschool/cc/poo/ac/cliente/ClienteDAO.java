package br.edu.cesarschool.cc.poo.ac.cliente;

import br.edu.cesarschool.cc.poo.ac.utils.SuperDAO;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class ClienteDAO extends SuperDAO {

    @Override
    protected Class<?> obterTipo() {
        return Cliente.class;
    }
    
    private CadastroObjetos cadastro = new CadastroObjetos(Cliente.class);

    private String obterIdUnico(Cliente cliente) {
        return cliente.getCpf();
    }

    public Cliente buscar(String cpf) {
        return (Cliente) cadastro.buscar(cpf);
    }

    public boolean incluir(Cliente cliente) {
        String idUnico = obterIdUnico(cliente);
        Cliente cli = buscar(idUnico);
        if (cli == null) {
            cadastro.incluir(cliente, idUnico);
            return true;
        }
        return false;
    }

    public boolean alterar(Cliente cliente) {
        String idUnico = obterIdUnico(cliente);
        Cliente cli = buscar(idUnico);
        if (cli != null) {
            cadastro.alterar(cliente, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String cpf) {
        Cliente cli = buscar(cpf);
        if (cli != null) {
            cadastro.excluir(cpf);
            return true;
        }
        return false;
    }
}
