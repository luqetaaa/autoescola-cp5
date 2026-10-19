package br.com.fiap3esph.autoescola3esph.service;

import br.com.fiap3esph.autoescola3esph.domain.agenda.ValidacaoException;
import br.com.fiap3esph.autoescola3esph.domain.endereco.DadosConsultaCep;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class ConsultaCepService {
    private final RestClient restClient;

    public ConsultaCepService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://viacep.com.br")
                .build();
    }

    public DadosConsultaCep consultar(String cep) {
        String cepNormalizado = cep.replaceAll("\\D", "");
        if (!cepNormalizado.matches("\\d{8}")) {
            throw new ValidacaoException("CEP deve conter exatamente 8 dígitos!");
        }

        try {
            DadosConsultaCep resposta = restClient.get()
                    .uri("/ws/{cep}/json/", cepNormalizado)
                    .retrieve()
                    .body(DadosConsultaCep.class);

            if (resposta == null || Boolean.TRUE.equals(resposta.erro())) {
                throw new ValidacaoException("CEP não encontrado!");
            }
            return resposta;
        } catch (ValidacaoException e) {
            throw e;
        } catch (RestClientException e) {
            throw new ValidacaoException("Não foi possível consultar o ViaCEP no momento!");
        }
    }
}
