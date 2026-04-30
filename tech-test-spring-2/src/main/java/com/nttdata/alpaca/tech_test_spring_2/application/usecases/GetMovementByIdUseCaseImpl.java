package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.config.exceptions.custom.NotFoundException;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetMovementById;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.MovementRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetMovementByIdUseCaseImpl implements GetMovementById {

    private final MovementRepositoryPort movementRepositoryPort;

    public GetMovementByIdUseCaseImpl(MovementRepositoryPort movementRepositoryPort) {
        this.movementRepositoryPort = movementRepositoryPort;
    }

    @Override
    public Mono<Movement> getMovementById(Long id) {
        return movementRepositoryPort.getMovementById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Movement not found with id: " + id)));
    }
}
