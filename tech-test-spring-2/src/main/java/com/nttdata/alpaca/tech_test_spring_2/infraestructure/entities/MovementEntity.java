package com.nttdata.alpaca.tech_test_spring_2.infraestructure.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "Movements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovementEntity {

    @Id
    @Column("id")
    Long id;

    @Column("numero_cuenta")
    String numeroCuenta;

    @Column("tipo_cuenta")
    String tipoCuenta;

    @Column("saldo_inicial")
    Double saldoInicial;

    @Column("valor")
    Double valor;

    @Column("tipo_movimiento")
    String tipoMovimiento;

    @Column("saldo")
    Double saldo;

    @Column("estado")
    Boolean estado;
}
