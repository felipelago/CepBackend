package br.com.solution.cepbackend.domain.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String cpf) {
        super("Já existe usuário cadastrado com o CPF: " + cpf);
    }
}
