package br.com.solution.cepbackend.presentation.controllers;

import br.com.solution.cepbackend.application.dto.request.UserRegisterRequest;
import br.com.solution.cepbackend.application.dto.request.UserUpdateRequest;
import br.com.solution.cepbackend.application.dto.response.AdressResponse;
import br.com.solution.cepbackend.application.dto.response.UserRegisterResponse;
import br.com.solution.cepbackend.application.dto.response.UserResponse;
import br.com.solution.cepbackend.application.dto.response.UserUpdateResponse;
import br.com.solution.cepbackend.application.services.UserService;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/v1/usuarios",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(service.registerUser(request));
    }

    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<UserResponse>> listUsers() {
        return ResponseEntity.ok(service.listUsers());
    }

    @GetMapping("/listar-enderecos")
    public ResponseEntity<List<AdressResponse>> listAdresses() {
        return ResponseEntity.ok(service.listAdresses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserUpdateResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) throws JsonMappingException {
        return ResponseEntity.ok(service.updateUser(id, request));
    }
}
