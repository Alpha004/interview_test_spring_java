package com.nttdata.alpaca.tech_test_spring.infraestructure.repositories;

import com.nttdata.alpaca.tech_test_spring.infraestructure.entities.ReportCustomerMovementEntity;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IReportCustomerMovementRepository extends R2dbcRepository<ReportCustomerMovementEntity, Long> {

    @Query("SELECT * FROM report_customer_movements WHERE cliente_id = :clienteId")
    Flux<ReportCustomerMovementEntity> findByClienteId(Long clienteId);
}
