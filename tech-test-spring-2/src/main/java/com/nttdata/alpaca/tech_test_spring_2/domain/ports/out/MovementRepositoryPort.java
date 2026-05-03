package com.nttdata.alpaca.tech_test_spring_2.domain.ports.out;

import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementRepositoryPort {
    Mono<Movement> saveMovement(Movement movement);
    Mono<Movement> getMovementById(Long id);
    Flux<Movement> getMovementsByAccount(String numeroCuenta);
    Mono<Movement> getLastMovementByAccount(String numeroCuenta);
    Mono<Long> countMovementsByAccount(String numeroCuenta);
}
