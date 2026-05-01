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
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AccountRequest {

	@NotBlank(message = "Account number cannot be empty")
    @Pattern(
        regexp = "^[0-9-]+$",
        message = "Account number must contain only numbers and hyphens"
    )
	@Size(max = 20)
    String numeroCuenta;

	@NotBlank(message = "Account type cannot be empty")
    @Pattern(
        regexp = "^(CORRIENTE|AHORRO)$",
        message = "Account type must be either 'Corriente' or 'Ahorro'"
    )
    String tipo;

    @NotNull(message = "Saldo Inicial cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Saldo Inicial cannot be negative")
    Double saldoInicial;

    Boolean estado = true;

    @NotBlank(message = "clienteNombre cannot be empty")
    String clienteNombre;
}
