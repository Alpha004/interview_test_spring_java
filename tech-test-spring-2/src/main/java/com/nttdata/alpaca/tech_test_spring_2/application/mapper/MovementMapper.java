package com.nttdata.alpaca.tech_test_spring_2.application.mapper;

import java.math.BigDecimal;

import com.nttdata.alpaca.tech_test_spring_2.config.utils.TipoMovement;
import com.nttdata.alpaca.tech_test_spring_2.config.utils.Utils;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.entities.MovementEntity;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.MovementEventDTO;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.LastMovementResponse;
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

    public static LastMovementResponse fromDomainToLastMovementResponse(Movement movement) {
        return new LastMovementResponse(
        		movement.getSaldo(),
        		movement.getTipoCuenta());
    }
    
    public static MovementEventDTO fromDomainToEvent(Movement movement) {
    	MovementEventDTO event = new MovementEventDTO();
    	event.setEvent(Utils.NEW_MOVEMENT_CREATED_EVENT);
    	event.setCliente(null);
    	event.setNumeroCuenta(movement.getNumeroCuenta());
    	event.setTipo(movement.getTipoCuenta());
    	event.setSaldoInicial(BigDecimal.valueOf(movement.getSaldoInicial()));
    	event.setValor(BigDecimal.valueOf(movement.getValor()));
    	event.setSaldo(BigDecimal.valueOf(movement.getSaldo()));
    	event.setTipoMovimiento(movement.getTipoMovimiento().toString());
    	return event;
    }
}
