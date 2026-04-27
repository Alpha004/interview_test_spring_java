package com.nttdata.alpaca.tech_test_spring.infraestructure.repositories;

import com.nttdata.alpaca.tech_test_spring.infraestructure.entities.ClienteEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends ReactiveCrudRepository<ClienteEntity, Long> {
}
