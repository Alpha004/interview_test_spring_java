package com.nttdata.alpaca.tech_test_spring_2.config.utils;

public final class Utils {

	
	public static Double calcularSaldo(Double saldoInicial, Double valor, String tipoMovimiento) {
	    if (TipoMovement.DEBITO.toString().equalsIgnoreCase(tipoMovimiento)) {
	        return saldoInicial - valor;
	    } else {
	        return saldoInicial + valor;
	    }
	}
}
