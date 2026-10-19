package br.com.fiap3esph.autoescola3esph.controller;

import br.com.fiap3esph.autoescola3esph.domain.endereco.DadosConsultaCep;
import br.com.fiap3esph.autoescola3esph.service.ConsultaCepService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
    private final ConsultaCepService service;

    @GetMapping("/cep/{cep}")
    @Operation(summary = "Consultar endereço por CEP", description = "Consome o WebService externo ViaCEP.")
    public ResponseEntity<DadosConsultaCep> consultarCep(@PathVariable String cep) {
        return ResponseEntity.ok(service.consultar(cep));
    }
}
