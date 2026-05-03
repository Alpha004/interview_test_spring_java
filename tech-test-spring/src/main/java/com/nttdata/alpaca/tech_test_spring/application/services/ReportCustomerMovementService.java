package com.nttdata.alpaca.tech_test_spring.application.services;

import org.springframework.stereotype.Service;

import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.GetReportCustomerMovementsByClienteId;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.SaveReportCustomerMovement;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReportCustomerMovementService {

    private final SaveReportCustomerMovement saveReportCustomerMovement;
    private final GetReportCustomerMovementsByClienteId getReportCustomerMovementsByClienteId;

    public Mono<ReportCustomerMovement> save(ReportCustomerMovement movement) {
        return saveReportCustomerMovement.save(movement);
    }

    public Flux<ReportCustomerMovement> getByClienteId(Long clienteId) {
        return getReportCustomerMovementsByClienteId.getByClienteId(clienteId);
    }
}
