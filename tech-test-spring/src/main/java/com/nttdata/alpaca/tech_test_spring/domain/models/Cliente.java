package com.nttdata.alpaca.tech_test_spring.domain.models;

import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

public class Cliente extends Persona {

    private String contrasenia;
    private Boolean estado;

    public Cliente(String nombre, String genero, String identificacion, String direccion, String telefono) {
        super(nombre, genero, identificacion, direccion, telefono);
    }
}