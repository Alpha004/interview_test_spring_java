package com.nttdata.alpaca.tech_test_spring.application.usecases;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.GetReportCustomerMovementsByClienteId;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ReportCustomerMovementRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class GetReportCustomerMovementsByClienteIdUseCaseImpl implements GetReportCustomerMovementsByClienteId {

    private final ReportCustomerMovementRepositoryPort repository;

    @Override
    public Flux<ReportCustomerMovement> getByClienteId(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }
}
