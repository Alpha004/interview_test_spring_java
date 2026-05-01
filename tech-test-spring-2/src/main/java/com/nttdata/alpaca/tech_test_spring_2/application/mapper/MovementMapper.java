package com.nttdata.alpaca.tech_test_spring_2.application.mapper;

import com.nttdata.alpaca.tech_test_spring_2.config.utils.TipoMovement;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.entities.MovementEntity;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.MovementRequest;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.MovementResponse;

public class MovementMapper {

    public static MovementEntity toEntity(Movement movement) {
        return MovementEntity.builder()
                .numeroCuenta(movement.getNumeroCuenta())
                .tipoCuenta(movement.getTipoCuenta())
                .saldoInicial(movement.getSaldoInicial())
                .valor(movement.getValor())
                .tipoMovimiento(movement.getTipoMovimiento().toString())
                .saldo(movement.getSaldo())
                .estado(movement.getEstado())
                .build();
    }

    public static Movement fromEntitytoDomain(MovementEntity entity) {
        Movement movimiento = new Movement();
        movimiento.setId(entity.getId());
        movimiento.setEstado(entity.getEstado());
        movimiento.setNumeroCuenta(entity.getNumeroCuenta());
        movimiento.setSaldo(entity.getSaldo());
        movimiento.setSaldoInicial(entity.getSaldoInicial());
        movimiento.setTipoCuenta(entity.getTipoCuenta());
        movimiento.setTipoMovimiento(TipoMovement.valueOf(entity.getTipoMovimiento().trim().toUpperCase()));
        movimiento.setValor(entity.getValor());
        return movimiento;
    }

    public static Movement fromRequestToDomain(MovementRequest request) {
    	Movement movimiento = new Movement();
        movimiento.setNumeroCuenta(request.getNumeroCuenta());        
//        movimiento.setSaldoInicial(request.getSaldoInicial());
//        movimiento.setTipoCuenta(request.getTipoCuenta());
        movimiento.setTipoMovimiento(TipoMovement.valueOf(request.getTipoMovimiento().trim().toUpperCase()));
        movimiento.setValor(request.getValor());
        return movimiento;
    }

    public static MovementResponse fromDomainToResponse(Movement movement) {
        return new MovementResponse(
        		movement.getNumeroCuenta(),
        		movement.getTipoCuenta(),
        		movement.getSaldoInicial(),
        		movement.getValor(),
        		movement.getTipoMovimiento().toString(),
        		movement.getSaldo());
    }

    public static com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.LastMovementResponse fromDomainToLastMovementResponse(Movement movement) {
        return new com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.LastMovementResponse(
        		movement.getSaldo(),
        		movement.getTipoCuenta());
    }
}
