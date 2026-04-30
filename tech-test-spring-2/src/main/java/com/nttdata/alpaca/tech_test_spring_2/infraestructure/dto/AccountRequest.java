package com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AccountRequest {

    @NotBlank(message = "numeroCuenta cannot be empty")
    String numeroCuenta;

    @NotBlank(message = "tipo cannot be empty")
    String tipo;

    @NotNull(message = "saldoInicial cannot be null")
    Double saldoInicial;

    Boolean estado;

    @NotBlank(message = "clienteNombre cannot be empty")
    String clienteNombre;
}
