package com.nttdata.alpaca.tech_test_spring.domain.ports.out;

import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;

import java.time.LocalDateTime;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReportCustomerMovementRepositoryPort {
    Mono<ReportCustomerMovement> save(ReportCustomerMovement movement);
    Flux<ReportCustomerMovement> getAllMovementsByCustomerName(String customerName);
    Flux<ReportCustomerMovement> getAllMovementsByCustomerNameAndDateRange(String customerName, LocalDateTime startDate, LocalDateTime endDate);
}
