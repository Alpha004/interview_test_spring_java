package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetMovementsByAccount;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.MovementRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class GetMovementsByAccountUseCaseImpl implements GetMovementsByAccount {

    private final MovementRepositoryPort movementRepositoryPort;

    public GetMovementsByAccountUseCaseImpl(MovementRepositoryPort movementRepositoryPort) {
        this.movementRepositoryPort = movementRepositoryPort;
    }

    @Override
    public Flux<Movement> getMovementsByAccount(String numeroCuenta) {
        return movementRepositoryPort.getMovementsByAccount(numeroCuenta);
    }
}
