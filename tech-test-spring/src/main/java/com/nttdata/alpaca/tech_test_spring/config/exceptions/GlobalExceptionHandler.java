package com.nttdata.alpaca.tech_test_spring.config.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.nttdata.alpaca.tech_test_spring.config.exceptions.custom.NotFoundException;

import org.springframework.dao.DuplicateKeyException;
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

	
	@ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleNotFound(NotFoundException ex, ServerHttpRequest request) {
		ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getPath().value(),
                Instant.now()
        );

        return Mono.just(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error));
    }
	
	@ExceptionHandler(DuplicateKeyException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleDuplicateKey(DuplicateKeyException ex, ServerHttpRequest request) {
		ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "There is an existing client with the same DNI",
                request.getPath().value(),
                Instant.now()
        );

        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error));
    }
	
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
