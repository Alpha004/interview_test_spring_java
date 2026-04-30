package com.nttdata.alpaca.tech_test_spring_2.application.mapper;

import com.nttdata.alpaca.tech_test_spring_2.domain.models.Account;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.entities.AccountEntity;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.AccountRequest;
import com.nttdata.alpaca.tech_test_spring_2.infraestructure.dto.AccountResponse;

public class AccountMapper {

    public static AccountEntity toEntity(Account account) {
        return AccountEntity.builder()
                .numeroCuenta(account.getNumeroCuenta())
                .tipo(account.getTipo())
                .saldoInicial(account.getSaldoInicial())
                .estado(account.getEstado())
                .clienteNombre(account.getClienteNombre())
                .build();
    }

    public static Account fromEntitytoDomain(AccountEntity entity) {
        return new Account();
    }

    public static Account fromRequestToDomain(AccountRequest request) {
        return new Account();
    }

    public static AccountResponse fromDomainToResponse(Account account) {
        return new AccountResponse();
    }
}
