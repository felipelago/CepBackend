package br.com.solution.cepbackend.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRegisterRequest(
        String nome,
        String cpf,
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        LocalDateTime data_atualizacao
) {
}
