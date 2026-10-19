package br.com.fiap3esph.autoescola3esph.domain.aluno;

import br.com.fiap3esph.autoescola3esph.domain.endereco.DadosEndereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {
    @Test
    void deveCadastrarEInativarAluno() {
        Aluno aluno = new Aluno(new DadosCadastroAluno(
                "João da Silva", "joao@email.com", "11999999999", "12345678901",
                endereco()));

        assertEquals("João da Silva", aluno.getNome());
        assertTrue(aluno.isAtivo());

        aluno.excluir();

        assertFalse(aluno.isAtivo());
    }

    private DadosEndereco endereco() {
        return new DadosEndereco("Avenida Paulista", "1000", "Apto 10", "Bela Vista",
                "São Paulo", "SP", "01310-100");
    }
}
