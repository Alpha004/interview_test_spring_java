package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.config.utils.Utils;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.CreateMovement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.MovementRepositoryPort;
import com.nttdata.alpaca.tech_test_spring_2.domain.rules.MovementValidator;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateMovementUseCaseImpl implements CreateMovement {

    private final MovementRepositoryPort movementRepositoryPort;

    public CreateMovementUseCaseImpl(MovementRepositoryPort movementRepositoryPort) {
        this.movementRepositoryPort = movementRepositoryPort;
    }

    @Override
    public Mono<Movement> createMovement(Movement movement) {
    	MovementValidator.validate(movement);
    	movement.setSaldo(Utils.calcularSaldo(movement.getSaldoInicial(), movement.getValor(), movement.getTipoMovimiento().toString()));
        return movementRepositoryPort.saveMovement(movement);
    }
}
