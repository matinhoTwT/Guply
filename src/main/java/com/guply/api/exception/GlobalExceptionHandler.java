package com.guply.api.exception;

import com.guply.api.dto.CampoErroDTO;
import com.guply.api.dto.ErroResponseDTO;
import com.guply.api.dto.ValidacaoErroResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidacaoErroResponseDTO tratarValidacao(MethodArgumentNotValidException ex) {
        List<CampoErroDTO> detalhes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> new CampoErroDTO(erro.getDefaultMessage(), erro.getField()))
                .toList();

        return new ValidacaoErroResponseDTO(
                "Dados inválidos",
                detalhes,
                400,
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponseDTO tratarRegraNegocio(RegraNegocioException ex) {
        return new ErroResponseDTO(
                ex.getMessage(),
                400,
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponseDTO tratarJsonInvalido(HttpMessageNotReadableException ex) {
        return new ErroResponseDTO(
                "JSON inválido ou data em formato errado. Use birthDate como yyyy-MM-dd",
                400,
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponseDTO tratarGenerico(Exception ex) {
        return new ErroResponseDTO(
                ex.getMessage(),
                400,
                LocalDateTime.now()
        );
    }
}
