package com.nttdata.alpaca.tech_test_spring.application.usecases;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.GetClienteById;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ClienteRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetClienteByIdUseCaseImpl implements GetClienteById {
	
	private final ClienteRepositoryPort clienteRepository;

	@Override
	public Mono<Cliente> getClienteById(Long id) {
		return clienteRepository.getClienteById(id);
	}

}