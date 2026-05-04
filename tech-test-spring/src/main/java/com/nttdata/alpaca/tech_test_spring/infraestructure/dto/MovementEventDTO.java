package com.nttdata.alpaca.tech_test_spring.infraestructure.dto;

import lombok.Data;

@Data
public class MovementEventDTO {
    private String event;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private Double valor;
    private Double saldo;
    private String tipoMovimiento;
}
