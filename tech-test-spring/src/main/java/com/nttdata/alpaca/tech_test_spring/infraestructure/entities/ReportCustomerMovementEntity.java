package com.nttdata.alpaca.tech_test_spring.infraestructure.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "report_customer_movements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ReportCustomerMovementEntity {
    @Id
    @Column("id")
    Long id;

    @Column("fecha")
    private LocalDateTime fecha;

    @Column("cliente_id")
    private Long clienteId;

    @Column("cliente")
    private String cliente;

    @Column("numero_cuenta")
    private String numeroCuenta;

    @Column("tipo")
    private String tipo;

    @Column("saldo_inicial")
    private BigDecimal saldoInicial;

    @Column("estado")
    private Boolean estado;

    @Column("valor_movimiento")
    private BigDecimal valorMovimiento;

    @Column("tipo_movimiento")
    private String tipoMovimiento;

    @Column("saldo_disponible")
    private BigDecimal saldoDisponible;

    @Column("created_at")
    private LocalDateTime createdAt;
}
