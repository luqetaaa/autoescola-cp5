package br.com.fiap3esph.autoescola3esph.domain.agenda;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InstrucaoTest {
    @Test
    void deveCriarECancelarInstrucao() {
        Instrucao instrucao = new Instrucao(null, null, null, LocalDateTime.now().plusDays(2));

        assertEquals(StatusInstrucao.AGENDADA, instrucao.getStatus());

        instrucao.cancelar(MotivoCancelamento.ALUNO_DESISTIU);

        assertEquals(StatusInstrucao.CANCELADA, instrucao.getStatus());
        assertEquals(MotivoCancelamento.ALUNO_DESISTIU, instrucao.getMotivoCancelamento());
        assertNotNull(instrucao.getDataCancelamento());
    }
}
