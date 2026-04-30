package com.nttdata.alpaca.tech_test_spring.infraestructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClienteRequest {

	@NotBlank(message = "Nombre cannot be empty")
    @Size(min = 2, max = 100, message = "Nombre debe tener al menos 2 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$", message = "Nombre must contain only letters")
    String nombre;

    @NotBlank(message = "Genero cannot be empty")
    String genero;

    @NotBlank(message = "Identificacion DNI cannot be empty")
    @Pattern(regexp = "^[0-9]{8}$", message = "DNI must be exactly 8 digits")
    String identificacion;

    String direccion;

    @Pattern(regexp = "^9\\d{8}$", message = "Telefono must be valid")
    String telefono;

    @Pattern(
            regexp = "^(?=\\S+$)(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long and contain upper, lower, digit, and special character"
    )
    String contrasenia;

    Boolean estado;

}
