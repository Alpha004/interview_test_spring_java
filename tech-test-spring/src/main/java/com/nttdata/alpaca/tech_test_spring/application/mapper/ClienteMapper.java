package com.nttdata.alpaca.tech_test_spring.application.mapper;

import com.nttdata.alpaca.tech_test_spring.config.encrypt.PasswordEncoder;
import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import com.nttdata.alpaca.tech_test_spring.infraestructure.entities.ClienteEntity;
import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.ClienteRequest;
import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.ClienteResponse;

public class ClienteMapper {

    public static ClienteEntity toEntity(Cliente cliente) {
        return ClienteEntity.builder()
                .nombre(cliente.getNombre())
                .genero(cliente.getGenero())
                .identificacion(cliente.getIdentificacion())
                .direccion(cliente.getDireccion())
                .telefono(cliente.getTelefono())
                .contrasenia(cliente.getContrasenia())
                .estado(cliente.getEstado())
                .build();
    }

    public static Cliente fromEntitytoDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getNombre(),
                entity.getGenero(),
                entity.getIdentificacion(),
                entity.getDireccion(),
                entity.getTelefono(),
                entity.getContrasenia(),
                entity.getEstado()
        );
    }

    public static Cliente fromRequestToDomain(ClienteRequest request) {
        return new Cliente(
                request.getNombre(),
                request.getGenero(),
                request.getIdentificacion(),
                request.getDireccion(),
                request.getTelefono(),
                PasswordEncoder.encode(request.getContrasenia()),
                null
        );
    }

    public static ClienteResponse fromDomainToResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getNombre(),
                cliente.getGenero(),
                cliente.getIdentificacion(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getEstado()
        );
    }
}
