package com.nttdata.alpaca.tech_test_spring.application.usecases;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.SaveReportCustomerMovement;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ReportCustomerMovementRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaveReportCustomerMovementUseCaseImpl implements SaveReportCustomerMovement {

    private final ReportCustomerMovementRepositoryPort repository;

    @Override
    public Mono<ReportCustomerMovement> save(ReportCustomerMovement movement) {
        return repository.save(movement);
    }
}
