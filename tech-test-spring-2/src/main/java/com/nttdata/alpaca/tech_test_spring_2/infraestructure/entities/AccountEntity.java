package com.nttdata.alpaca.tech_test_spring_2.infraestructure.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "Accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity {

    @Id
    @Column("id")
    Long id;

    @Column("numero_cuenta")
    String numeroCuenta;

    @Column("tipo")
    String tipo;

    @Column("saldo_inicial")
    Double saldoInicial;

    @Column("estado")
    Boolean estado;

    @Column("cliente_nombre")
    String clienteNombre;
}
