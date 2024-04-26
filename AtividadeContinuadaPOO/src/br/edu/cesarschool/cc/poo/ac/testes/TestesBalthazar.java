package br.edu.cesarschool.cc.poo.ac.testes;

import static org.junit.Assert.assertEquals;
import br.edu.cesarschool.cc.poo.ac.passagem.*;
import org.junit.Test;

public class TestesBalthazar {

    @Test
    public void testeInicializacaoTelaVoo() {
        TelaVoo telaVoo = new TelaVoo();
        assertEquals(true, telaVoo != null);
    }

    @Test
    public void testeInicializacaoProgramaCadastroVoo() {
        ProgramaCadastroVoo.main(new String[]{});
        // Aqui não há retorno direto para validar, mas se não houver exceções, considera-se sucesso
        assertEquals(true, true);
    }
}
