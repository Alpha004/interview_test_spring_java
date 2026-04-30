package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.UpdateAccount;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.AccountRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateAccountUseCaseImpl implements UpdateAccount {

    private final AccountRepositoryPort accountRepositoryPort;

    public UpdateAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Mono<Account> updateAccount(Long id, Account account) {
        return accountRepositoryPort.getAccountById(id)
                .flatMap(existing -> accountRepositoryPort.updateAccount(id, account));
    }
}
