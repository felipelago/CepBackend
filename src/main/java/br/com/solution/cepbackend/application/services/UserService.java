package br.com.solution.cepbackend.application.services;

import br.com.solution.cepbackend.application.dto.request.UserRegisterRequest;
import br.com.solution.cepbackend.application.dto.request.UserUpdateRequest;
import br.com.solution.cepbackend.application.dto.response.*;
import br.com.solution.cepbackend.domain.entities.UserEntity;
import br.com.solution.cepbackend.domain.exceptions.DuplicateResourceException;
import br.com.solution.cepbackend.domain.exceptions.ResourceNotFoundException;
import br.com.solution.cepbackend.domain.logic.CepValidator;
import br.com.solution.cepbackend.domain.logic.CpfValidator;
import br.com.solution.cepbackend.infrastructure.repositories.UserRepository;
import br.com.solution.cepbackend.infrastructure.viacep.ViaCepClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final ViaCepClient viaCepClient;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper, ViaCepClient viaCepClient) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.viaCepClient = viaCepClient;
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
    public UserUpdateResponse updateUser(Long id, UserUpdateRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + id));

        user.setNome(request.nome());
        user.setCpf(request.cpf());
        user.setCep(request.cep());

        ViaCepResponse viaCepResponse = findViaCep(request.cep());
        user.setLogradouro(viaCepResponse.logradouro());
        user.setBairro(viaCepResponse.bairro());
        user.setCidade(viaCepResponse.cidade());
        user.setEstado(viaCepResponse.estado());

        user.setDataAtualizacao(LocalDateTime.now());

        UserEntity saved = userRepository.save(user);
        return objectMapper.convertValue(saved, UserUpdateResponse.class);
    }

    private ViaCepResponse findViaCep(String cep) {
        CepValidator.validate(cep);

        return viaCepClient.findByCep(cep);
    }
}
