package br.com.solution.cepbackend.domain.exceptions;

public class InvalidCpfException extends RuntimeException {
    public InvalidCpfException(String cpf) {
        super("CPF em formato inválido: " + cpf);
    }
}
