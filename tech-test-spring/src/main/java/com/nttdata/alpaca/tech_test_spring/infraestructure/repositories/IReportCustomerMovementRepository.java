package com.nttdata.alpaca.tech_test_spring.infraestructure.repositories;

import com.nttdata.alpaca.tech_test_spring.infraestructure.entities.ReportCustomerMovementEntity;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.time.LocalDateTime;

import reactor.core.publisher.Flux;

public interface IReportCustomerMovementRepository extends R2dbcRepository<ReportCustomerMovementEntity, Long> {

    @Query("SELECT * FROM report_customer_movements WHERE cliente = :cliente")
    Flux<ReportCustomerMovementEntity> findByCliente(String cliente);

    @Query("SELECT * FROM report_customer_movements WHERE cliente = :cliente AND created_at BETWEEN :startDate AND :endDate")
    Flux<ReportCustomerMovementEntity> findByClienteAndDateRange(String cliente, LocalDateTime startDate, LocalDateTime endDate);
}
