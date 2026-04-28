package com.nttdata.alpaca.tech_test_spring.infraestructure.dto;

public record ClienteRequest(String nombre, String genero, String identificacion, String direccion, String telefono, String contrasenia) {

}
