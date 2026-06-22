package com.guply.api.dto;

import java.time.LocalDateTime;

public record ErroResponseDTO(
        String erro,
        int status,
        LocalDateTime timestamp
) {
}
