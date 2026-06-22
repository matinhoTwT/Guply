package com.guply.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ValidacaoErroResponseDTO(
        String erro,
        List<CampoErroDTO> detalhes,
        int status,
        LocalDateTime timestamp
) {
}
