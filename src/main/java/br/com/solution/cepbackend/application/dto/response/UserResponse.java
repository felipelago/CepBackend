package br.com.solution.cepbackend.application.dto.response;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String nome,
        String cpf,
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        LocalDateTime dataCriacao
) {
}
