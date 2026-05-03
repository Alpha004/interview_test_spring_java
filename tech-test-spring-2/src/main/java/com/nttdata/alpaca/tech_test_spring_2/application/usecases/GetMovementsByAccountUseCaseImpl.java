package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.config.exceptions.custom.NotFoundException;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetMovementsByAccount;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.MovementRepositoryPort;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetMovementsByAccountUseCaseImpl implements GetMovementsByAccount {

    private final MovementRepositoryPort movementRepositoryPort;

    @Override
    public Flux<Movement> getMovementsByAccount(String numeroCuenta) {
        return movementRepositoryPort.getMovementsByAccount(numeroCuenta)
        		.switchIfEmpty(Mono.error(new NotFoundException("Movements not found by Account number: " + numeroCuenta)));
    }
}
