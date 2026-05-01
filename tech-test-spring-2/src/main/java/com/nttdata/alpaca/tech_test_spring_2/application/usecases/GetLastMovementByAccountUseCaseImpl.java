package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.config.exceptions.custom.NotFoundException;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetLastMovementByAccount;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.MovementRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetLastMovementByAccountUseCaseImpl implements GetLastMovementByAccount {

    private final MovementRepositoryPort movementRepositoryPort;

    public GetLastMovementByAccountUseCaseImpl(MovementRepositoryPort movementRepositoryPort) {
        this.movementRepositoryPort = movementRepositoryPort;
    }

    @Override
    public Mono<Movement> getLastMovementByAccount(String numeroCuenta) {
        return movementRepositoryPort.getLastMovementByAccount(numeroCuenta)
                .switchIfEmpty(Mono.error(new NotFoundException("No movements found for account: " + numeroCuenta)));
    }
}
