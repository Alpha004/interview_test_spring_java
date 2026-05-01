package com.nttdata.alpaca.tech_test_spring_2.domain.models;

import com.nttdata.alpaca.tech_test_spring_2.config.utils.TipoMovement;

import lombok.Data;

@Data
public class Movement {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private Double valor;
    private TipoMovement tipoMovimiento;
    private Double saldo;
    private Boolean estado;
}
