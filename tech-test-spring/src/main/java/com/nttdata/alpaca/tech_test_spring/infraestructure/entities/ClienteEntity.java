package com.nttdata.alpaca.tech_test_spring.infraestructure.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

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

    @Column("nombre")
    private String nombre;

    @Column("genero")
    private String genero;

    @Column("dni")
    private String identificacion;

    @Column("direccion")
    private String direccion;

    @Column("telefono")
    private String telefono;

    @Column("contrasenia")
    private String contrasenia;

    @Column("estado")
    private Boolean estado;
}
