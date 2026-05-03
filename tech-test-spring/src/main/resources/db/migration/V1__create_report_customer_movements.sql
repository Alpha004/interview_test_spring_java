CREATE TABLE report_customer_movements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    cliente_id BIGINT NOT NULL,
    cliente VARCHAR(100) NOT NULL,
    numero_cuenta VARCHAR(20) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    saldo_inicial DECIMAL(12,2) NOT NULL,
    estado BOOLEAN NOT NULL,
    valor_movimiento DECIMAL(12,2) NOT NULL,
    tipo_movimiento VARCHAR(20) NOT NULL,
    saldo_disponible DECIMAL(12,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_cliente_id(cliente_id),
    INDEX idx_cliente (cliente),
    INDEX idx_fecha (fecha),
    INDEX idx_cliente_fecha (cliente, fecha),
    INDEX idx_numero_cuenta (numero_cuenta)
);
