package br.com.solution.cepbackend.application.services;

import br.com.solution.cepbackend.application.dto.request.UserRegisterRequest;
import br.com.solution.cepbackend.application.dto.request.UserUpdateRequest;
import br.com.solution.cepbackend.application.dto.response.AdressResponse;
import br.com.solution.cepbackend.application.dto.response.UserRegisterResponse;
import br.com.solution.cepbackend.application.dto.response.UserResponse;
import br.com.solution.cepbackend.application.dto.response.UserUpdateResponse;
import br.com.solution.cepbackend.application.repositories.UserRepositoryPort;
import br.com.solution.cepbackend.domain.entities.UserEntity;
import br.com.solution.cepbackend.domain.exceptions.DuplicateResourceException;
import br.com.solution.cepbackend.domain.exceptions.ResourceNotFoundException;
import br.com.solution.cepbackend.domain.logic.CpfValidator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepositoryPort userRepository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepositoryPort userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public UserRegisterResponse registerUser(UserRegisterRequest request) {
        CpfValidator.validate(request.cpf());

        if (userRepository.findByCpf(request.cpf()).isPresent()) {
            throw new DuplicateResourceException("CPF Já cadastrado: " + request.cpf());
        }

        UserEntity entity = objectMapper.convertValue(request, UserEntity.class);
        UserEntity salvo = userRepository.save(entity);

        return objectMapper.convertValue(salvo, UserRegisterResponse.class);
    }

    @Transactional
    public List<UserResponse> listUsers() {
        return userRepository.listUsers();
    }

    @Transactional
    public List<AdressResponse> listAdresses() {
        return userRepository.listAdresses();
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id))
            throw new ResourceNotFoundException("Usuário não encontrado: " + id);
        userRepository.deleteById(id);
    }

    @Transactional
    public UserUpdateResponse updateUser(Long id, UserUpdateRequest request) throws JsonMappingException {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));

        objectMapper.updateValue(user, request);

        user.setDataAtualizacao(LocalDateTime.now());

        UserEntity saved = userRepository.save(user);
        return objectMapper.convertValue(saved, UserUpdateResponse.class);
    }
}
