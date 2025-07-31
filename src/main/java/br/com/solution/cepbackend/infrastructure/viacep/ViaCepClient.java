package br.com.solution.cepbackend.infrastructure.viacep;

import br.com.solution.cepbackend.application.dto.response.ViaCepResponse;
import br.com.solution.cepbackend.domain.exceptions.InvalidCepException;
import br.com.solution.cepbackend.domain.exceptions.ResourceNotFoundException;
import br.com.solution.cepbackend.domain.logic.CepValidator;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCepClient {

    private final RestTemplate rt;

    public ViaCepClient(RestTemplateBuilder b) {
        this.rt = b
                .rootUri("https://viacep.com.br/ws")
                .build();
    }

    public ViaCepResponse findByCep(String rawCep) {
        String cep = CepValidator.validateAndNormalize(rawCep);

        ViaCepResponse response;
        try {
            response = rt.getForObject("/{cep}/json", ViaCepResponse.class, cep);
        } catch (HttpClientErrorException.BadRequest ex) {
            throw new InvalidCepException("CEP inválido segundo ViaCEP: " + rawCep);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("CEP não encontrado: " + rawCep);
        }
        return response;
    }
}
