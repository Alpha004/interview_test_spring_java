package com.nttdata.alpaca.tech_test_spring.domain.models;

import lombok.Data;

@Data
public class Cliente extends Persona {

    private String contrasenia;
    private Boolean estado;

    public Cliente(String nombre, String genero, String identificacion, String direccion, String telefono, String contra, Boolean estado) {
        super(nombre, genero, identificacion, direccion, telefono);
        this.contrasenia = contra;
        this.estado = estado;
    }
}