package br.com.solution.cepbackend.presentation.controllers;

import br.com.solution.cepbackend.application.dto.request.UsuarioCadastroRequest;
import br.com.solution.cepbackend.application.dto.response.UsuarioCadastroResponse;
import br.com.solution.cepbackend.application.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioCadastroResponse> cadastrarUsuario(@Valid @RequestBody UsuarioCadastroRequest request) {
        return ResponseEntity.ok(service.cadastrarUsuario(request));
    }
}
