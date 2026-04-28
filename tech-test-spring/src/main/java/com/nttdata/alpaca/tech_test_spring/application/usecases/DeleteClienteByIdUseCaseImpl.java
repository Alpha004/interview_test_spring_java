package com.nttdata.alpaca.tech_test_spring.application.usecases;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.domain.ports.in.DeleteClienteByid;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ClienteRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteClienteByIdUseCaseImpl implements DeleteClienteByid {
	
	private final ClienteRepositoryPort clienteRepository;

	@Override
	public Mono<Void> deleteClienteById(Long id) {
		return clienteRepository.deleteClienteById(id);
	}

}