package br.com.solution.cepbackend.presentation.controllers;

import br.com.solution.cepbackend.application.dto.request.UserRegisterRequest;
import br.com.solution.cepbackend.application.dto.response.AdressResponse;
import br.com.solution.cepbackend.application.dto.response.UserRegisterResponse;
import br.com.solution.cepbackend.application.dto.response.UserResponse;
import br.com.solution.cepbackend.application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/v1/cadastrar")
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(service.registerUser(request));
    }

    @GetMapping("/v1/listar-usuarios")
    public ResponseEntity<List<UserResponse>> listUsers() {
        return ResponseEntity.ok(service.listUsers());
    }

    @GetMapping("/v1/listar-enderecos")
    public ResponseEntity<List<AdressResponse>> listAdresses() {
        return ResponseEntity.ok(service.listAdresses());
    }

    @DeleteMapping("/v1/delete-user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
