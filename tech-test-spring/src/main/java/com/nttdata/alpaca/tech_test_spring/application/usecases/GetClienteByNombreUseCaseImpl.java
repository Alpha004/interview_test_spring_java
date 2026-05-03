package com.nttdata.alpaca.tech_test_spring.application.usecases;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.config.exceptions.custom.NotFoundException;
import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.GetClienteByNombre;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ClienteRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetClienteByNombreUseCaseImpl implements GetClienteByNombre{
	
	private final ClienteRepositoryPort clienteRepository;
	
	@Override
	public Mono<Cliente> getClienteByNombre(String nombre) {
		return clienteRepository.getClienteByNombre(nombre)
				.switchIfEmpty(Mono.error(new NotFoundException("Cliente no encontrado con nombre: " + nombre)));
	}

}
