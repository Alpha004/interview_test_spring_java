package com.nttdata.alpaca.tech_test_spring.infraestructure.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.application.mapper.ClienteMapper;
import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ClienteRepositoryPort;
import com.nttdata.alpaca.tech_test_spring.infraestructure.entities.ClienteEntity;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JpaClienteRepositoryAdapter implements ClienteRepositoryPort {

	private final IClienteRepository clienteRepository;
	@Override
	public Flux<Cliente> getAllClientes(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.clienteRepository.findAllPageable(pageable).map(ClienteMapper::fromEntitytoDomain);
	}

	@Override
	public Mono<Cliente> saveCliente(Cliente cliente) {
		return this.clienteRepository.save(ClienteMapper.toEntity(cliente))
				.map(ClienteMapper::fromEntitytoDomain);
	}

	@Override
	public Mono<Cliente> updateCliente(Long id, Cliente cliente) {
		ClienteEntity entity = ClienteMapper.toEntity(cliente);
		entity.setId(id);
		return this.clienteRepository.save(entity).map(ClienteMapper::fromEntitytoDomain);
	}

	@Override
	public Mono<Cliente> getClienteById(Long id) {
		return this.clienteRepository.findById(id).map(ClienteMapper::fromEntitytoDomain);
	}

	@Override
	public Mono<Void> deleteClienteById(Long id) {
		return this.clienteRepository.deleteById(id);
	}

}
