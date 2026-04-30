package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.CreateAccount;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.AccountRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateAccountUseCaseImpl implements CreateAccount {

    private final AccountRepositoryPort accountRepositoryPort;

    public CreateAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Mono<Account> createAccount(Account account) {
        return accountRepositoryPort.saveAccount(account);
    }
}
