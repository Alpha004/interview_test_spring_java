package com.nttdata.alpaca.tech_test_spring_2.domain.ports.out;

import org.springframework.data.domain.Pageable;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepositoryPort {
    Flux<Account> getAllAccounts(Pageable pageable);
    Mono<Account> saveAccount(Account account);
    Mono<Account> updateAccount(Long id, Account account);
    Mono<Account> getAccountById(Long id);
    Mono<Account> getAccountByNumeroCuenta(String numeroCuenta);
    Mono<Void> deleteAccountById(Long id);
}
