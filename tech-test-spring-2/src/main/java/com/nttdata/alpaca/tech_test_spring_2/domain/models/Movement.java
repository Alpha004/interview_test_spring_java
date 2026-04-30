package com.nttdata.alpaca.tech_test_spring_2.domain.models;

import lombok.Data;

@Data
public class Movement {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Double valor;
    private String tipoMovimiento;
    private Double saldo;
    private Boolean estado;
}
