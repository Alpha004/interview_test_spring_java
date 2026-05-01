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
        Account account = new Account();
        account.setId(entity.getId());
        account.setClienteNombre(entity.getClienteNombre());
        account.setNumeroCuenta(entity.getNumeroCuenta());
        account.setSaldoInicial(entity.getSaldoInicial());
        account.setTipo(entity.getTipo());
        account.setEstado(entity.getEstado());
    	return account;
    }

    public static Account fromRequestToDomain(AccountRequest request) {
    	Account account = new Account();
//        account.setId(request.get());
        account.setClienteNombre(request.getClienteNombre());
        account.setNumeroCuenta(request.getNumeroCuenta());
        account.setSaldoInicial(request.getSaldoInicial());
        account.setTipo(request.getTipo());
        account.setEstado(request.getEstado());
    	return account;
    }

    public static AccountResponse fromDomainToResponse(Account account) {
    	return new AccountResponse(
    			account.getNumeroCuenta(),
    			account.getTipo(),
    			account.getSaldoInicial(),
    			account.getEstado(),
    			account.getClienteNombre());
    }
}
