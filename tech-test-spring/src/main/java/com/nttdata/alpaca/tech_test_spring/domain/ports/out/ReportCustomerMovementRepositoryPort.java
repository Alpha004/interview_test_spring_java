package com.nttdata.alpaca.tech_test_spring.domain.ports.out;

import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReportCustomerMovementRepositoryPort {
    Mono<ReportCustomerMovement> save(ReportCustomerMovement movement);
    Flux<ReportCustomerMovement> findByClienteId(Long clienteId);
}
