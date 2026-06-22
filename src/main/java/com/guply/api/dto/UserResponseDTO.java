package com.guply.api.dto;

import com.guply.api.model.Department;
import com.guply.api.model.Role;

import java.time.LocalDate;

public record UserResponseDTO(
        Long id,
        String name,
        String cpf,
        String email,
        LocalDate birthDate,
        String phone,
        Department department,
        Role role
) {
}
