package br.com.solution.cepbackend.application.dto.response;

import java.time.LocalDateTime;

public record AdressResponse(
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        LocalDateTime dataCriacao
) {
}
