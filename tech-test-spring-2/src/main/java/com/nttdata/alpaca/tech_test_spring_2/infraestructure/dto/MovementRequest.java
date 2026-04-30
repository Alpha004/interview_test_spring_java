package com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class MovementRequest {

    @NotNull(message = "numeroCuenta cannot be null")
    String numeroCuenta;

    @NotNull(message = "tipoCuenta cannot be null")
    String tipoCuenta;

    @NotNull(message = "saldoInicial cannot be null")
    Double saldoInicial;

    @NotNull(message = "valor cannot be null")
    Double valor;

    @NotNull(message = "tipoMovimiento cannot be null")
    String tipoMovimiento;

    @NotNull(message = "saldo cannot be null")
    Double saldo;
}
