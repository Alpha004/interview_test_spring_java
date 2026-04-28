package com.nttdata.alpaca.tech_test_spring.application.usecases;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.GetAllClientes;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ClienteRepositoryPort;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class GetAllClientsUseCaseImpl implements GetAllClientes{
		
	private final ClienteRepositoryPort clienteRepository;
	
	@Override
	public Flux<Cliente> getAllClientes(Pageable page) {
		return clienteRepository.getAllClientes(page);
	}

}
