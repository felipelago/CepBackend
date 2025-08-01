package br.com.solution.cepbackend.infrastructure.repositories;

import br.com.solution.cepbackend.application.dto.response.AdressResponse;
import br.com.solution.cepbackend.application.dto.response.UserResponse;
import br.com.solution.cepbackend.application.repositories.UserRepositoryPort;
import br.com.solution.cepbackend.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryPort {

    Optional<UserEntity> findByCpf(String cpf);

    @Query("""
                select new br.com.solution.cepbackend.application.dto.response.UserResponse(
                    u.id,
                    u.nome,
                    u.cpf,
                    u.cep,
                    u.logradouro,
                    u.bairro,
                    u.cidade,
                    u.estado,
                    u.dataCriacao
                )
                from UserEntity u
            """)
    List<UserResponse> listUsers();

    @Query("""
                select new br.com.solution.cepbackend.application.dto.response.AdressResponse(
                    u.logradouro,
                    u.bairro,
                    u.cidade,
                    u.estado,
                    u.dataCriacao
                )
                from UserEntity u
            """)
    List<AdressResponse> listAdresses();
}
