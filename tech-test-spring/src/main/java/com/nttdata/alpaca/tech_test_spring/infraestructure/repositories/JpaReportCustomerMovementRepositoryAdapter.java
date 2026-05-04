package com.nttdata.alpaca.tech_test_spring.infraestructure.repositories;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.application.mapper.ReportCustomerMovementMapper;
import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ReportCustomerMovementRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JpaReportCustomerMovementRepositoryAdapter implements ReportCustomerMovementRepositoryPort {

    private final IReportCustomerMovementRepository repository;

    @Override
    public Mono<ReportCustomerMovement> save(ReportCustomerMovement movement) {
        return repository.save(ReportCustomerMovementMapper.toEntity(movement))
                .map(ReportCustomerMovementMapper::fromEntityToDomain);
    }

	@Override
	public Flux<ReportCustomerMovement> getAllMovementsByCustomerName(String customerName) {
		return repository.findByCliente(customerName)
				.map(ReportCustomerMovementMapper::fromEntityToDomain);
	}

	@Override
	public Flux<ReportCustomerMovement> getAllMovementsByCustomerNameAndDateRange(String customerName, LocalDateTime startDate, LocalDateTime endDate) {
		return repository.findByClienteAndDateRange(customerName, startDate, endDate)
				.map(ReportCustomerMovementMapper::fromEntityToDomain);
	}
}
