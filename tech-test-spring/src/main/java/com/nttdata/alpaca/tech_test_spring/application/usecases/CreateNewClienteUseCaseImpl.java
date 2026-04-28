package com.nttdata.alpaca.tech_test_spring.application.usecases;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.CreateNewCliente;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ClienteRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateNewClienteUseCaseImpl implements CreateNewCliente{
	
	private final ClienteRepositoryPort clientRepository;

	public Mono<Cliente> saveCliente(Cliente cliente) {
		return this.clientRepository.saveCliente(cliente);
	}

}
