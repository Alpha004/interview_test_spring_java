package com.nttdata.alpaca.tech_test_spring.infraestructure.repositories;

import com.nttdata.alpaca.tech_test_spring.infraestructure.entities.ClienteEntity;

import reactor.core.publisher.Flux;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends ReactiveCrudRepository<ClienteEntity, Long> {
	
	@Query("SELECT * FROM Cliente LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}")
	Flux<ClienteEntity> findAllPageable(Pageable pageable);
}
