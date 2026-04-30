package com.nttdata.alpaca.tech_test_spring_2.application.mapper;

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
                .tipoMovimiento(movement.getTipoMovimiento())
                .saldo(movement.getSaldo())
                .estado(movement.getEstado())
                .build();
    }

    public static Movement fromEntitytoDomain(MovementEntity entity) {
        return new Movement();
    }

    public static Movement fromRequestToDomain(MovementRequest request) {
        return new Movement();
    }

    public static MovementResponse fromDomainToResponse(Movement movement) {
        return new MovementResponse();
    }
}
