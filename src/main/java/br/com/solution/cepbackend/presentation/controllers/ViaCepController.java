package br.com.solution.cepbackend.presentation.controllers;

import br.com.solution.cepbackend.application.dto.response.ViaCepResponse;
import br.com.solution.cepbackend.infrastructure.viacep.ViaCepClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/viaCep")
public class ViaCepController {

    private final ViaCepClient viaCepClient;

    public ViaCepController(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @GetMapping("/v1/{cep}")
    public ResponseEntity<ViaCepResponse> findAddress(@PathVariable String cep) {
        return ResponseEntity.ok(viaCepClient.findByCep(cep));
    }
}
