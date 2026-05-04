package com.nttdata.alpaca.tech_test_spring.application.mapper;

import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;
import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.MovementEventDTO;
import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.ReportItem;
import com.nttdata.alpaca.tech_test_spring.infraestructure.entities.ReportCustomerMovementEntity;

public class ReportCustomerMovementMapper {
	
    public static ReportCustomerMovement fromAccountEventToEntity(MovementEventDTO accountEvent) {
    	return ReportCustomerMovement.builder()				
				.cliente(accountEvent.getCliente())
				.numeroCuenta(accountEvent.getNumeroCuenta())
				.tipo(accountEvent.getTipo())
				.saldoInicial(accountEvent.getSaldoInicial())
				.valorMovimiento(accountEvent.getValor())
				.tipoMovimiento(accountEvent.getTipoMovimiento())
				.saldoDisponible(accountEvent.getSaldo())
				.estado(true)
				.build();
    }

	public static ReportCustomerMovementEntity toEntity(ReportCustomerMovement movement) {
        return ReportCustomerMovementEntity.of(
                movement.getId(),
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

	public static ReportCustomerMovement fromEntityToDomain(ReportCustomerMovementEntity entity) {
        return ReportCustomerMovement.builder()
                .id(entity.getId())
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

	public static ReportItem toReportItem(ReportCustomerMovement movement) {
		return ReportItem.builder()
				.fecha(movement.getCreatedAt() != null ? movement.getCreatedAt().toLocalDate() : null)
				.cliente(movement.getCliente())
				.numeroCuenta(movement.getNumeroCuenta())
				.tipo(movement.getTipo())
				.saldoInicial(movement.getSaldoInicial())
				.estado(movement.getEstado())
				.valorMovimiento(movement.getValorMovimiento())
				.tipoMovimiento(movement.getTipoMovimiento())
				.saldoDisponible(movement.getSaldoDisponible())
				.build();
	}
}
