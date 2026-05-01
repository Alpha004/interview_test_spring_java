package com.nttdata.alpaca.tech_test_spring_2.domain.rules;

import com.nttdata.alpaca.tech_test_spring_2.config.exceptions.custom.InsufficientBalanceException;
import com.nttdata.alpaca.tech_test_spring_2.config.utils.TipoMovement;
import com.nttdata.alpaca.tech_test_spring_2.domain.models.Movement;

public class MovementValidator {

	public static void validate(Movement movement) {
        validateMovementNotNull(movement);
        validateValor(movement.getValor());        
        validateSufficientBalance(movement);
//        return true;
    }
    private static void validateMovementNotNull(Movement movement) {
        if (movement == null) {
            throw new IllegalArgumentException("El movimiento no puede ser nulo");
        }
    }
    private static void validateValor(Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("El valor de un movimiento debe ser mayor que cero");
        }
    }
    
    private static void validateSufficientBalance(Movement movement) {
        if (movement.getTipoMovimiento() == TipoMovement.DEBITO) {
            if (movement.getSaldoInicial() < 0) {
                throw new InsufficientBalanceException("Saldo no disponible");
            }
        }
    }
}
