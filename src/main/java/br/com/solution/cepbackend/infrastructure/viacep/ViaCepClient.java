package br.com.solution.cepbackend.infrastructure.viacep;

import br.com.solution.cepbackend.application.dto.response.ViaCepResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCepClient {

    private final RestTemplate rt;

    public ViaCepClient(RestTemplateBuilder b) {
        this.rt = b
                .rootUri("https://viacep.com.br/ws")
                .build();
    }

    public ViaCepResponse findByCep(String cep) {
        return rt.getForObject("/{cep}/json", ViaCepResponse.class, cep);
    }
}
