package br.com.solution.cepbackend.domain.logic;

import br.com.solution.cepbackend.domain.exceptions.InvalidCpfException;

public class CpfValidator {

    public static void validate(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new InvalidCpfException("CPF em formato inválido: " + cpf);
        }
    }
}
