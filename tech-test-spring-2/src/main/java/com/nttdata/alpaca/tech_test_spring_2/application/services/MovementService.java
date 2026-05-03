package com.nttdata.alpaca.tech_test_spring_2.application.services;

import com.nttdata.alpaca.tech_test_spring_2.application.mapper.MovementMapper;
import com.nttdata.alpaca.tech_test_spring_2.config.mq.IMessageQueueProducer;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.CountMovementsByAccount;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.CreateMovement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetAccountByNumeroCuenta;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetLastMovementByAccount;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetMovementById;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetMovementsByAccount;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.MovementEventDTO;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Service
@RequiredArgsConstructor
public class MovementService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovementService.class);
	
	@Value("${app.kafka.topic.cliente-topic}")
	private String clienteTopic;

    private final CreateMovement createMovement;
    private final GetMovementById getMovementById;
    private final GetMovementsByAccount getMovementsByAccount;
    private final GetLastMovementByAccount getLastMovementByAccount;
    private final CountMovementsByAccount countMovementsByAccount;
    private final GetAccountByNumeroCuenta getAccountByNumeroCuenta;
    private final IMessageQueueProducer mqProducer;
    

    public Mono<Movement> saveMovement(Movement movement) {
    	return this.getLastMovementByAccount.getLastMovementByAccount(movement.getNumeroCuenta())
    			.flatMap(mov -> {
    				movement.setSaldoInicial(mov.getSaldo());
    				movement.setTipoCuenta(mov.getTipoCuenta());
    				return this.getAccountByNumeroCuenta.getAccountByNumeroCuenta(movement.getNumeroCuenta())
    				.map(account -> {
                        return Tuples.of(movement, account.getClienteNombre());
                    });
    			})
    			.switchIfEmpty(
    					this.getAccountByNumeroCuenta.getAccountByNumeroCuenta(movement.getNumeroCuenta())
	    					.map(account -> {
	                            movement.setSaldoInicial(account.getSaldoInicial());
	                            movement.setTipoCuenta(account.getTipo());
	                            return Tuples.of(movement, account.getClienteNombre());
	                        }))
    			.flatMap(tuple ->
    						createMovement.createMovement(tuple.getT1())
    						.map(saved -> Tuples.of(saved,tuple.getT2())
				))
    			.doOnSuccess(tupla -> {
    				Movement savedMovement = tupla.getT1();
    				String clienteNombre = tupla.getT2();
    				LOGGER.info("Movement ready to be send to the mq");
    				MovementEventDTO event = MovementMapper.fromDomainToEvent(savedMovement);
    				if(!clienteNombre.isBlank()) {
    					event.setCliente(clienteNombre);
    				}
    				mqProducer.sendMovement(event, clienteTopic);
    			})
    			.map(Tuple2::getT1);
    }

    public Mono<Movement> getMovementById(Long id) {
        return getMovementById.getMovementById(id);
    }

    public Flux<Movement> getMovementsByAccount(String numeroCuenta) {
        return getMovementsByAccount.getMovementsByAccount(numeroCuenta);
    }

    public Mono<Movement> getLastMovementByAccount(String numeroCuenta) {
        return getLastMovementByAccount.getLastMovementByAccount(numeroCuenta);
    }

    public Mono<Long> countMovementsByAccount(String numeroCuenta) {
        return countMovementsByAccount.countMovementsByAccount(numeroCuenta);
    }
}
