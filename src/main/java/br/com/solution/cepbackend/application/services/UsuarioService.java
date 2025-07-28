package br.com.solution.cepbackend.application.services;

import br.com.solution.cepbackend.application.dto.request.UsuarioCadastroRequest;
import br.com.solution.cepbackend.application.dto.response.UsuarioCadastroResponse;
import br.com.solution.cepbackend.domain.entities.UsuarioEntity;
import br.com.solution.cepbackend.domain.exceptions.UserAlreadyExistsException;
import br.com.solution.cepbackend.domain.logic.CpfValidator;
import br.com.solution.cepbackend.infrastructure.repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ObjectMapper objectMapper) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public UsuarioCadastroResponse cadastrarUsuario(UsuarioCadastroRequest request) {
        CpfValidator.validate(request.cpf());

        if (usuarioRepository.findByCpf(request.cpf()).isPresent()) {
            throw new UserAlreadyExistsException(request.cpf());
        }

        UsuarioEntity entity = objectMapper.convertValue(request, UsuarioEntity.class);
        UsuarioEntity salvo = usuarioRepository.save(entity);

        return objectMapper.convertValue(salvo, UsuarioCadastroResponse.class);
    }

}
