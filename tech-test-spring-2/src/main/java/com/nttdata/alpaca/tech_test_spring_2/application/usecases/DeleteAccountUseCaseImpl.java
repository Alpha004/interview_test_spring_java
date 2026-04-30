package com.nttdata.alpaca.tech_test_spring_2.application.usecases;

import com.nttdata.alpaca.tech_test_spring_2.domain.ports.in.DeleteAccount;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.AccountRepositoryPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DeleteAccountUseCaseImpl implements DeleteAccount {

    private final AccountRepositoryPort accountRepositoryPort;

    public DeleteAccountUseCaseImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Mono<Void> deleteAccountById(Long id) {
        return accountRepositoryPort.deleteAccountById(id);
    }
}
