package com.nttdata.alpaca.tech_test_spring_2.domain.models;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private String numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private Boolean estado;
    private String clienteNombre;
}
