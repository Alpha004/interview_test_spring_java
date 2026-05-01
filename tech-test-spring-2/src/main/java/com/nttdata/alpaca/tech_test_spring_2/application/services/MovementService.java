package com.nttdata.alpaca.tech_test_spring_2.application.services;

import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.CreateMovement;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetLastMovementByAccount;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetMovementById;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetMovementsByAccount;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementService {

    private final CreateMovement createMovement;
    private final GetMovementById getMovementById;
    private final GetMovementsByAccount getMovementsByAccount;
    private final GetLastMovementByAccount getLastMovementByAccount;

    public MovementService(CreateMovement createMovement, GetMovementById getMovementById, GetMovementsByAccount getMovementsByAccount, GetLastMovementByAccount getLastMovementByAccount) {
        this.createMovement = createMovement;
        this.getMovementById = getMovementById;
        this.getMovementsByAccount = getMovementsByAccount;
        this.getLastMovementByAccount = getLastMovementByAccount;
    }

    public Mono<Movement> saveMovement(Movement movement) {
    	return this.getLastMovementByAccount.getLastMovementByAccount(movement.getNumeroCuenta())
    			.map(mov -> {
    				movement.setSaldoInicial(mov.getSaldo());
    				movement.setTipoCuenta(mov.getTipoCuenta());
    				return movement;
    			})
    			.flatMap(createMovement::createMovement);    			
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
}
