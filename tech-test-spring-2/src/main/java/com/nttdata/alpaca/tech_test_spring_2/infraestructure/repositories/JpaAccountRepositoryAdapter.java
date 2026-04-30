package com.nttdata.alpaca.tech_test_spring_2.infraestructure.repositories;

import com.nttdata.alpaca.tech_test_spring_2.application.mapper.AccountMapper;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.domain.ports.out.AccountRepositoryPort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class JpaAccountRepositoryAdapter implements AccountRepositoryPort {

    private final IAccountRepository accountRepository;

    public JpaAccountRepositoryAdapter(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Flux<Account> getAllAccounts(Pageable pageable) {
        return accountRepository.findAllPaginated(pageable.getPageSize(), (int) pageable.getOffset())
                .map(AccountMapper::fromEntitytoDomain);
    }

    @Override
    public Mono<Account> saveAccount(Account account) {
        AccountEntity entity = AccountMapper.toEntity(account);
        return accountRepository.save(entity)
                .map(AccountMapper::fromEntitytoDomain);
    }

    @Override
    public Mono<Account> updateAccount(Long id, Account account) {
        return accountRepository.findById(id)
                .flatMap(existing -> {
                    AccountEntity updated = AccountMapper.toEntity(account);
                    updated.setId(id);
                    return accountRepository.save(updated);
                })
                .map(AccountMapper::fromEntitytoDomain);
    }

    @Override
    public Mono<Account> getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(AccountMapper::fromEntitytoDomain);
    }

    @Override
    public Mono<Void> deleteAccountById(Long id) {
        return accountRepository.deleteById(id);
    }
}
