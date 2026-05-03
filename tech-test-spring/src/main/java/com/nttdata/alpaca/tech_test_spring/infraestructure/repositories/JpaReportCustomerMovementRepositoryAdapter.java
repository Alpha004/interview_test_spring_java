package com.nttdata.alpaca.tech_test_spring.infraestructure.repositories;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ReportCustomerMovementRepositoryPort;
import com.nttdata.alpaca.tech_test_spring.infraestructure.entities.ReportCustomerMovementEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JpaReportCustomerMovementRepositoryAdapter implements ReportCustomerMovementRepositoryPort {

    private final IReportCustomerMovementRepository repository;

    @Override
    public Mono<ReportCustomerMovement> save(ReportCustomerMovement movement) {
        return repository.save(toEntity(movement))
                .map(this::fromEntityToDomain);
    }

    @Override
    public Flux<ReportCustomerMovement> findByClienteId(Long clienteId) {
        return repository.findByClienteId(clienteId)
                .map(this::fromEntityToDomain);
    }

    private ReportCustomerMovementEntity toEntity(ReportCustomerMovement movement) {
        return ReportCustomerMovementEntity.of(
                movement.getId(),
                movement.getFecha(),
                movement.getClienteId(),
                movement.getCliente(),
                movement.getNumeroCuenta(),
                movement.getTipo(),
                movement.getSaldoInicial(),
                movement.getEstado(),
                movement.getValorMovimiento(),
                movement.getTipoMovimiento(),
                movement.getSaldoDisponible(),
                movement.getCreatedAt()
        );
    }

    private ReportCustomerMovement fromEntityToDomain(ReportCustomerMovementEntity entity) {
        return ReportCustomerMovement.builder()
                .id(entity.getId())
                .fecha(entity.getFecha())
                .clienteId(entity.getClienteId())
                .cliente(entity.getCliente())
                .numeroCuenta(entity.getNumeroCuenta())
                .tipo(entity.getTipo())
                .saldoInicial(entity.getSaldoInicial())
                .estado(entity.getEstado())
                .valorMovimiento(entity.getValorMovimiento())
                .tipoMovimiento(entity.getTipoMovimiento())
                .saldoDisponible(entity.getSaldoDisponible())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
