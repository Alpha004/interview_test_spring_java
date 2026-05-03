package com.nttdata.alpaca.tech_test_spring.domain.models;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportCustomerMovement {
    private Long id;    
    private Long clienteId;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private BigDecimal valorMovimiento;
    private String tipoMovimiento;
    private BigDecimal saldoDisponible;
    private LocalDateTime createdAt;
}
