package com.nttdata.alpaca.tech_test_spring.infraestructure.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.alpaca.tech_test_spring.application.mapper.ClienteMapper;
import com.nttdata.alpaca.tech_test_spring.application.services.ClienteService;
import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.ClienteRequest;
import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.ClienteResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/api/customers")
@RequiredArgsConstructor
public class ClienteRestController {
	
	private final ClienteService clienteService;
	
	@GetMapping
	public Mono<ResponseEntity<List<ClienteResponse>>> getListOfClients(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return this.clienteService.getClients(pageable)
				.map(ClienteMapper::fromDomainToResponse)
				.collectList()
				.map(ResponseEntity::ok);
	}
	
	@PostMapping
	public Mono<ResponseEntity<ClienteResponse>> saveNewClient(@RequestBody @Valid ClienteRequest cliente){
		return this.clienteService.saveCliente(ClienteMapper.fromRequestToDomain(cliente))
				.map(ClienteMapper::fromDomainToResponse)
				.map(response -> ResponseEntity
	                    .created(URI.create("/api/v1/customers"))
	                    .body(response));
	}
	
	@PutMapping("/{clientId}")
	public Mono<ResponseEntity<ClienteResponse>> updateExistingClientById(@RequestBody @Valid ClienteRequest cliente, @PathVariable Long clientId){
		return this.clienteService.updateCliente(ClienteMapper.fromRequestToDomain(cliente),clientId)
				.map(ClienteMapper::fromDomainToResponse)
				.map(response -> ResponseEntity.accepted().body(response));
	}
	
	@DeleteMapping("/{clientId}")
	public Mono<ResponseEntity<Void>> deleteClientById(@PathVariable Long clientId){
		return this.clienteService.deleteCliente(clientId)
				.map(ResponseEntity::ok);
	}
	
	@GetMapping("/{clientId}")
	public Mono<ResponseEntity<ClienteResponse>> getClientById(@PathVariable Long clientId){
		return this.clienteService.getClienteById(clientId)
				.map(ClienteMapper::fromDomainToResponse)
				.map(ResponseEntity::ok);
	}
}
