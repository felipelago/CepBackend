package br.com.solution.cepbackend.application.repositories;

import br.com.solution.cepbackend.application.dto.response.AdressResponse;
import br.com.solution.cepbackend.application.dto.response.UserResponse;
import br.com.solution.cepbackend.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    UserEntity save(UserEntity user);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByCpf(String cpf);

    boolean existsById(Long id);

    void deleteById(Long id);

    List<UserResponse> listUsers();

    List<AdressResponse> listAdresses();
}
