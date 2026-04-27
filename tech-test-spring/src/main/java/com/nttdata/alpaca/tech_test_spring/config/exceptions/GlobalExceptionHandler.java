package com.nttdata.alpaca.tech_test_spring.config.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleRuntimeException(
            RuntimeException ex,
            ServerHttpRequest request
    ) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getPath().value(),
                Instant.now()
        );

        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error));
    }

    @ExceptionHandler(InvalidFormatException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleInvalidEnum(InvalidFormatException ex, ServerHttpRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getPath().value(),
                Instant.now()
        );
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error));
    }

//    @ExceptionHandler(DuplicateAlumnoException.class)
//    public Mono<ResponseEntity<ErrorResponse>> handleInvalidEnum(DuplicateAlumnoException ex, ServerHttpRequest request) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.CONFLICT.value(),
//                ex.getMessage(),
//                request.getPath().value(),
//                Instant.now()
//        );
//        return Mono.just(ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .body(error));
//    }

    @ExceptionHandler(ServerWebInputException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleServerWebInput(
            ServerWebInputException ex, ServerHttpRequest request) {

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Valor inválido para el campo estado, solo se permiten ACTIVO/INACTIVO",
                request.getPath().value(),
                Instant.now()
        );
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleValidationExceptions(WebExchangeBindException ex, ServerHttpRequest request) {
        //Map<String, String> errors = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            //errors.put(error.getField(), error.getDefaultMessage());
            builder.append(error.getDefaultMessage());
            builder.append(System.lineSeparator());
        }
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                builder.toString(),
                request.getPath().value(),
                Instant.now()
        );
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error));
    }

}
