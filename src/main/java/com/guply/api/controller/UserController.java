package com.guply.api.controller;

import com.guply.api.dto.UserRequestDTO;
import com.guply.api.dto.UserResponseDTO;
import com.guply.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO criar(@Valid @RequestBody UserRequestDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<UserResponseDTO> listar() {
        return service.listar();
    }
}
