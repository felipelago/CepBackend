package br.com.solution.cepbackend.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRegisterResponse(
        String nome,
        String cpf,
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        LocalDateTime data_criacao
) {
}
