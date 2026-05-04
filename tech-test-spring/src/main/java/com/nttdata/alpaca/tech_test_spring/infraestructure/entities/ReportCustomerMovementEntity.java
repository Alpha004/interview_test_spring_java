package com.nttdata.alpaca.tech_test_spring.infraestructure.entities;

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

    @Column("cliente")
    private String cliente;

    @Column("numero_cuenta")
    private String numeroCuenta;

    @Column("tipo")
    private String tipo;

    @Column("saldo_inicial")
    private Double saldoInicial;

    @Column("estado")
    private Boolean estado;

    @Column("valor_movimiento")
    private Double valorMovimiento;

    @Column("tipo_movimiento")
    private String tipoMovimiento;

    @Column("saldo_disponible")
    private Double saldoDisponible;

    @Column("created_at")
    private LocalDateTime createdAt;
}
