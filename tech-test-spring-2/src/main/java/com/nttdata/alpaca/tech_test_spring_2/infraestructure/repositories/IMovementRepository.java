package com.nttdata.alpaca.tech_test_spring_2.infraestructure.repositories;

import com.nttdata.alpaca.tech_test_spring_2.infraestructure.entities.MovementEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IMovementRepository extends ReactiveCrudRepository<MovementEntity, Long> {

    Flux<MovementEntity> findByNumeroCuenta(String numeroCuenta);

    @Query("SELECT * FROM Movements WHERE numero_cuenta = :numeroCuenta ORDER BY created_at DESC")
    Flux<MovementEntity> findMovementsByAccountOrdered(String numeroCuenta);
}
