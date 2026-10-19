package br.com.fiap3esph.autoescola3esph.domain.instrutor;

import br.com.fiap3esph.autoescola3esph.domain.endereco.DadosEndereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstrutorTest {
    @Test
    void deveCadastrarEInativarInstrutor() {
        Instrutor instrutor = new Instrutor(new DadosCadastroInstrutor(
                "Carlos Oliveira", "carlos@autoescola.com", "11988887777", "12345678901",
                Especialidade.CARROS, endereco()));

        assertEquals(Especialidade.CARROS, instrutor.getEspecialidade());
        assertTrue(instrutor.isAtivo());

        instrutor.excluir();

        assertFalse(instrutor.isAtivo());
    }

    private DadosEndereco endereco() {
        return new DadosEndereco("Rua das Flores", "200", null, "Centro",
                "São Paulo", "SP", "01001-000");
    }
}
