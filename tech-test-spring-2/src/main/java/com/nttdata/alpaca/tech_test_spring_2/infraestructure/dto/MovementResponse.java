package com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto;

public record MovementResponse(String numeroCuenta, String tipoCuenta, Double saldoInicial, Double valor, String tipoMovimiento, Double saldo) {
}
