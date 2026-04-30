package com.nttdata.alpaca.tech_test_spring_2.domain.ports.in;

import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import reactor.core.publisher.Flux;

public interface GetMovementsByAccount {
    Flux<Movement> getMovementsByAccount(String numeroCuenta);
}
