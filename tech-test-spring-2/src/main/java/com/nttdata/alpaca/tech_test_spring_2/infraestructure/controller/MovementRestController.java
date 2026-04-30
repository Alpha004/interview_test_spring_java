package com.nttdata.alpaca.tech_test_spring_2.infraestructure.controller;

import com.nttdata.alpaca.tech_test_spring_2.application.mapper.MovementMapper;
import com.nttdata.alpaca.tech_test_spring_2.application.services.MovementService;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.MovementRequest;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.MovementResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/api/movements")
public class MovementRestController {

    private final MovementService movementService;

    public MovementRestController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping("/account/{numeroCuenta}")
    public Flux<MovementResponse> getMovementsByAccount(@PathVariable String numeroCuenta) {
        return movementService.getMovementsByAccount(numeroCuenta)
                .map(MovementMapper::fromDomainToResponse);
    }

    @GetMapping("/{movementId}")
    public Mono<MovementResponse> getMovementById(@PathVariable Long movementId) {
        return movementService.getMovementById(movementId)
                .map(MovementMapper::fromDomainToResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovementResponse> createMovement(@RequestBody MovementRequest request) {
        Movement movement = MovementMapper.fromRequestToDomain(request);
        return movementService.saveMovement(movement)
                .map(MovementMapper::fromDomainToResponse);
    }
}
