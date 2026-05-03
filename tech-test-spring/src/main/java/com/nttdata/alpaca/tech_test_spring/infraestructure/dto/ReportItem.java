package com.nttdata.alpaca.tech_test_spring.infraestructure.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReportItem {
	private LocalDate fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private Boolean estado;
    private Double valorMovimiento;
    private String tipoMovimiento;
    private Double saldoDisponible;
}
