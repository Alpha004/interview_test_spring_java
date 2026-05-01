package com.nttdata.alpaca.tech_test_spring_2.infraestructure.repositories;

import com.nttdata.alpaca.tech_test_spring_2.application.mapper.MovementMapper;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.MovementRepositoryPort;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.entities.MovementEntity;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class JpaMovementRepositoryAdapter implements MovementRepositoryPort {

    private final IMovementRepository movementRepository;

    public JpaMovementRepositoryAdapter(IMovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @Override
    public Mono<Movement> saveMovement(Movement movement) {
        MovementEntity entity = MovementMapper.toEntity(movement);
        return movementRepository.save(entity)
                .map(MovementMapper::fromEntitytoDomain);
    }

    @Override
    public Mono<Movement> getMovementById(Long id) {
        return movementRepository.findById(id)
                .map(MovementMapper::fromEntitytoDomain);
    }

    @Override
    public Flux<Movement> getMovementsByAccount(String numeroCuenta) {
        return movementRepository.findMovementsByAccountOrdered(numeroCuenta)
                .map(MovementMapper::fromEntitytoDomain);
    }

    @Override
    public Mono<Movement> getLastMovementByAccount(String numeroCuenta) {
        return movementRepository.findLastMovementByAccount(numeroCuenta)
                .map(MovementMapper::fromEntitytoDomain);
    }
}
