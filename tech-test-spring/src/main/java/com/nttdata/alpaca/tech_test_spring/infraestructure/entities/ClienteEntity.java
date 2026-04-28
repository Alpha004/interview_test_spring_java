package com.nttdata.alpaca.tech_test_spring.infraestructure.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table(name = "Cliente")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClienteEntity {

    @Id
    @Column("id")
    Long id;

    @NotNull(message = "Nombre cannot be empty")
    @Size(min = 2, max = 100, message = "Nombre debe tener al menos 2 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+$", message = "Nombre must contain only letters")
    @Column("nombre")
    private String nombre;

    @NotNull(message = "Genero cannot be empty")
    @Column("genero")
    private String genero;

    @NotNull(message = "Identificacion DNI cannot be empty")
    @Pattern(regexp = "^[0-9]{8}$", message = "DNI must be exactly 8 digits")
    @Column("dni")
    private String identificacion;
    private String direccion;

    @Column("telefono")
    @Pattern(regexp = "^9\\d{8}$", message = "Telefono must be valid")
    private String telefono;

    @Pattern(
            regexp = "^(?=\\\\S+$)(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long and contain upper, lower, digit, and special character"
    )
    @Column("contrasenia")
    private String contrasenia;
    private Boolean estado;

    @Column("creation_date")
    private Date creation_timestamp;
}
