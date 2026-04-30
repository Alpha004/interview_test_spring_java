package com.nttdata.alpaca.tech_test_spring.application.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.nttdata.alpaca.tech_test_spring.application.usecases.GetAllClientsUseCaseImpl;
import com.nttdata.alpaca.tech_test_spring.domain.models.Cliente;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.CreateNewCliente;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.DeleteClienteByid;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.GetAllClientes;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.GetClienteById;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.UpdateClienteById;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClienteService {
	private final GetAllClientes getAllClients;
	private final CreateNewCliente createNewCliente;
	private final GetClienteById getClienteById;
	private final UpdateClienteById updateClienteById;
	private final DeleteClienteByid deleteClienteById;

	
	public Flux<Cliente> getClients(Pageable page){
		return this.getAllClients.getAllClientes(page);
	}
	
	public Mono<Cliente> saveCliente(Cliente cliente){
		return this.createNewCliente.saveCliente(cliente);
	}
	
	public Mono<Cliente> getClienteById(Long id){
		return this.getClienteById.getClienteById(id);
	}
	
	public Mono<Cliente> updateCliente(Cliente cliente, Long id){
		return this.updateClienteById.updateCliente(id, cliente);
	}
	
	public Mono<Void> deleteCliente(Long id){
		return this.deleteClienteById.deleteClienteById(id);
	}
	
}
