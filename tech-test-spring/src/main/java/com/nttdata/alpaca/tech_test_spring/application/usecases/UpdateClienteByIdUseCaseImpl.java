package com.nttdata.alpaca.tech_test_spring.application.usecases;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.UpdateClienteById;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ClienteRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateClienteByIdUseCaseImpl implements UpdateClienteById {
	
	private final ClienteRepositoryPort clienteRepository;

	@Override
	public Mono<Cliente> updateCliente(Long id, Cliente cliente) {
		return clienteRepository.getClienteById(id)
				.map(cli -> {
					cli.setNombre(cliente.getNombre());
					cli.setGenero(cliente.getGenero());
					cli.setIdentificacion(cliente.getIdentificacion());
					cli.setDireccion(cliente.getDireccion());
					cli.setTelefono(cliente.getTelefono());
					if (cliente.getContrasenia() != null) {
						cli.setContrasenia(cliente.getContrasenia());
					}
					if (cliente.getEstado() != null) {
						cli.setEstado(cliente.getEstado());
					}					
					return cli;
				})
				.flatMap(updatedCliente -> clienteRepository.updateCliente(id, updatedCliente));
	}

}