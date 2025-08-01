package br.com.solution.cepbackend.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserUpdateRequest(

        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos numéricos, sem hífen")
        String cep,

        @JsonSetter(nulls = Nulls.SKIP)
        String logradouro,

        @JsonSetter(nulls = Nulls.SKIP)
        String bairro,

        @JsonSetter(nulls = Nulls.SKIP)
        String cidade,

        @JsonSetter(nulls = Nulls.SKIP)
        String estado
) {
}
