package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.config.exceptions.custom.NotFoundException;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.GetAccountById;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.AccountRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetAccountByIdUseCaseImpl implements GetAccountById {

    private final AccountRepositoryPort accountRepositoryPort;

    public GetAccountByIdUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Mono<Account> getAccountById(Long id) {
        return accountRepositoryPort.getAccountById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Account not found with id: " + id)));
    }
}
