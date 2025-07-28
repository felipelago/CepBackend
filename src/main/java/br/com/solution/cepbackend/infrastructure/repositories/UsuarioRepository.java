package br.com.solution.cepbackend.infrastructure.repositories;

import br.com.solution.cepbackend.domain.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<Object> findByCpf(String cpf);
}
