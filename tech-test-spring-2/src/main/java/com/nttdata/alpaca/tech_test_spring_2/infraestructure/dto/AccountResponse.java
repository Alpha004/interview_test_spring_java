package com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto;

public record AccountResponse(String numeroCuenta, String tipo, Double saldoInicial, Boolean estado, String clienteNombre) {
}
