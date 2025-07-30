package br.com.solution.cepbackend.domain.logic;

import br.com.solution.cepbackend.domain.exceptions.InvalidCepException;

public class CepValidator {

    public static void validate(String cep) {
        if (cep == null || !cep.matches("\\d{8}")) {
            throw new InvalidCepException("CEP em formato inválido: " + cep);
        }
    }
}
