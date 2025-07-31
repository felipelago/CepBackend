package br.com.solution.cepbackend.domain.logic;

import br.com.solution.cepbackend.domain.exceptions.InvalidCepException;

public class CepValidator {

    private CepValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Verifica se o CEP tem exatamente 8 dígitos (0-9).
     *
     * @param cep o CEP que vem do cliente
     * @return o próprio CEP (sem alteração), se válido
     * @throws InvalidCepException se CEP for null, tiver tamanho diferente de 8 ou conter caractere não numérico
     */
    public static String validateAndNormalize(String cep) {
        if (cep == null || cep.isBlank()) {
            throw new InvalidCepException("CEP não pode ser vazio");
        }
        String trimmed = cep.trim();
        if (!trimmed.matches("\\d{8}")) {
            throw new InvalidCepException("Formato de CEP inválido. Deve conter exatamente 8 dígitos: " + cep);
        }
        return trimmed;
    }
}
