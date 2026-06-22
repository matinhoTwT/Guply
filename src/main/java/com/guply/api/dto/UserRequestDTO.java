package com.guply.api.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 6, message = "O nome deve ter mais de 5 caracteres")
        String name,

        @NotBlank(message = "O CPF é obrigatório")
        @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
        String cpf,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        LocalDate birthDate,

        String phone,

        @NotBlank(message = "O departamento é obrigatório")
        String department,

        @NotBlank(message = "O cargo é obrigatório")
        String role
) {
}
