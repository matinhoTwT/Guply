package com.guply.api.service;

import com.guply.api.dto.UserRequestDTO;
import com.guply.api.dto.UserResponseDTO;
import com.guply.api.exception.RegraNegocioException;
import com.guply.api.model.Department;
import com.guply.api.model.Role;
import com.guply.api.model.User;
import com.guply.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserResponseDTO criar(UserRequestDTO dto) {
        Department department = converterDepartamento(dto.department());
        Role role = converterCargo(dto.role());

        if (repository.existsByEmail(dto.email())) {
            throw new RegraNegocioException("Email já cadastrado: " + dto.email());
        }

        if (repository.existsByCpf(dto.cpf())) {
            throw new RegraNegocioException("CPF já cadastrado: " + dto.cpf());
        }

        User user = User.builder()
                .name(dto.name())
                .cpf(dto.cpf())
                .email(dto.email())
                .birthDate(dto.birthDate())
                .phone(dto.phone())
                .department(department)
                .role(role)
                .build();

        return toResponse(repository.save(user));
    }

    public List<UserResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private Department converterDepartamento(String valor) {
        try {
            return Department.valueOf(valor.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RegraNegocioException(
                    "Valor inválido para department: '" + valor + "'. Valores válidos: " + valoresValidos(Department.class)
            );
        }
    }

    private Role converterCargo(String valor) {
        try {
            return Role.valueOf(valor.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RegraNegocioException(
                    "Valor inválido para role: '" + valor + "'. Valores válidos: " + valoresValidos(Role.class)
            );
        }
    }

    private <E extends Enum<E>> String valoresValidos(Class<E> enumClass) {
        return String.join(", ", Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).toList());
    }

    private UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getEmail(),
                user.getBirthDate(),
                user.getPhone(),
                user.getDepartment(),
                user.getRole()
        );
    }
}
