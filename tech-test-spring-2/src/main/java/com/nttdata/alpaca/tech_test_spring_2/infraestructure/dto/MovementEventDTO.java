package com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MovementEventDTO {
    private String event;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private BigDecimal valor;
    private BigDecimal saldo;
    private String tipoMovimiento;
}
