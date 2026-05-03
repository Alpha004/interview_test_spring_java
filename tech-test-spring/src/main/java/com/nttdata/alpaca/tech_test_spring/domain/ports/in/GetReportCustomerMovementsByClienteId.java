package com.nttdata.alpaca.tech_test_spring.domain.ports.in;

import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;

import reactor.core.publisher.Flux;

public interface GetReportCustomerMovementsByClienteId {
    Flux<ReportCustomerMovement> getByClienteId(Long clienteId);
}
