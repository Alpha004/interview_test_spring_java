package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.config.exceptions.custom.NotFoundException;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetAccountByNumeroCuenta;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.AccountRepositoryPort;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetAccountByNumeroCuentaUseCaseImpl implements GetAccountByNumeroCuenta {

    private final AccountRepositoryPort accountRepositoryPort;

    @Override
    public Mono<Account> getAccountByNumeroCuenta(String numeroCuenta) {
        return accountRepositoryPort.getAccountByNumeroCuenta(numeroCuenta)
                .switchIfEmpty(Mono.error(new NotFoundException("Account not found with numeroCuenta: " + numeroCuenta)));
    }
}
