package com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class MovementRequest {

	@NotBlank(message = "Account number cannot be empty")
    @Pattern(
        regexp = "^[0-9-]+$",
        message = "Account number must contain only numbers and hyphens"
    )
	@Size(max = 20)
    private String numeroCuenta;

//    @NotBlank(message = "Account type cannot be empty")
//    @Pattern(
//        regexp = "^(CORRIENTE|AHORRO)$",
//        message = "Account type must be either 'Corriente' or 'Ahorros'"
//    )
//    private String tipoCuenta;

//    @NotNull(message = "Saldo Inicial cannot be null")
//    @DecimalMin(value = "0.0", inclusive = true, message = "Saldo Inicial cannot be negative")
//    private Double saldoInicial;

    @NotNull(message = "Valor cannot be null")
    @DecimalMin(value = "1.0", inclusive = true, message = "Amount must be greater than or equal to 1")
    private Double valor;

    @NotBlank(message = "Movement type cannot be empty")
    @Pattern(
        regexp = "^(DEBITO|CREDITO)$",
        message = "Movement type must be either 'Debito' or 'Credito'"
    )
    private String tipoMovimiento;
}
