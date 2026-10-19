package br.com.fiap3esph.autoescola3esph.domain.usuario;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    @Test
    void deveAtualizarPerfilESenha() {
        Usuario usuario = new Usuario("usuario1", "senhaCriptografada", Role.USER);

        usuario.atualizarPerfil(Role.ADMIN);
        usuario.alterarSenha("novaSenhaCriptografada");

        assertEquals(Role.ADMIN, usuario.getPerfil());
        assertEquals("novaSenhaCriptografada", usuario.getSenha());
        assertTrue(usuario.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }
}
