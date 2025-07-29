package br.com.solution.cepbackend.application.services;

import br.com.solution.cepbackend.application.dto.request.UserRegisterRequest;
import br.com.solution.cepbackend.application.dto.response.AdressResponse;
import br.com.solution.cepbackend.application.dto.response.UserRegisterResponse;
import br.com.solution.cepbackend.application.dto.response.UserResponse;
import br.com.solution.cepbackend.domain.entities.UserEntity;
import br.com.solution.cepbackend.domain.exceptions.DuplicateResourceException;
import br.com.solution.cepbackend.domain.exceptions.ResourceNotFoundException;
import br.com.solution.cepbackend.domain.logic.CpfValidator;
import br.com.solution.cepbackend.infrastructure.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
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

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id))
            throw new ResourceNotFoundException("User não encontrado: " + id);
        userRepository.deleteById(id);
    }
}
