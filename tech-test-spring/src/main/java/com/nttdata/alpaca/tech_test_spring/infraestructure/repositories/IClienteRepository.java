package com.nttdata.alpaca.tech_test_spring.infraestructure.repositories;

import com.nttdata.alpaca.tech_test_spring.infraestructure.entities.ClienteEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClienteRepository extends R2dbcRepository<ClienteEntity, Long> {

    @Query("SELECT * FROM Cliente LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}")
    Flux<ClienteEntity> findAllPageable(Pageable pageable);

    @Query("SELECT * FROM Cliente WHERE id = :clienteId")
    Mono<ClienteEntity> findById(Long clienteId);
    
    //@Query("SELECT * FROM Cliente WHERE nombre = :nombre")
    Mono<ClienteEntity> findByNombre(String nombre);
}
