package com.nttdata.alpaca.tech_test_spring_2.domain.ports.in;

import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import reactor.core.publisher.Mono;

public interface GetAccountByNumeroCuenta {
    Mono<Account> getAccountByNumeroCuenta(String numeroCuenta);
}
