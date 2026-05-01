package com.nttdata.alpaca.tech_test_spring_2.domain.ports.in;

import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import reactor.core.publisher.Mono;

public interface GetLastMovementByAccount {
    Mono<Movement> getLastMovementByAccount(String numeroCuenta);
}
