package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.CountMovementsByAccount;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.MovementRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CountMovementsByAccountUseCaseImpl implements CountMovementsByAccount {

    private final MovementRepositoryPort movementRepositoryPort;

    public CountMovementsByAccountUseCaseImpl(MovementRepositoryPort movementRepositoryPort) {
        this.movementRepositoryPort = movementRepositoryPort;
    }

    @Override
    public Mono<Long> countMovementsByAccount(String numeroCuenta) {
        return movementRepositoryPort.countMovementsByAccount(numeroCuenta);
    }
}
