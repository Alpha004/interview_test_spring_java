package com.nttdata.alpaca.tech_test_spring.domain.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Persona {

    private String nombre;
    private String genero;
    private String identificacion;
    private String direccion;
    private String telefono;
}