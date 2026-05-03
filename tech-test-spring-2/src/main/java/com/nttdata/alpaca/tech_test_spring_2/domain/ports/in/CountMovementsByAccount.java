package com.nttdata.alpaca.tech_test_spring_2.domain.ports.in;

import reactor.core.publisher.Mono;

public interface CountMovementsByAccount {
    Mono<Long> countMovementsByAccount(String numeroCuenta);
}
