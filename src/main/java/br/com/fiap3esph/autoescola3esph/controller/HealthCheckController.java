package br.com.fiap3esph.autoescola3esph.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {
    @GetMapping
    @SecurityRequirements
    public String healthCheck() {
        return "Verificação de integridade da API da Auto Escola 3ESPH!";
    }
}
