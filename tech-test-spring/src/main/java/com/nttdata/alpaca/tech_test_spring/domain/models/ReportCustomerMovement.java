package com.nttdata.alpaca.tech_test_spring.domain.models;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportCustomerMovement {
    private Long id;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private Boolean estado;
    private Double valorMovimiento;
    private String tipoMovimiento;
    private Double saldoDisponible;
    private LocalDateTime createdAt;
}
