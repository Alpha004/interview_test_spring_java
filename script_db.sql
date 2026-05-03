USE pichincha_interv;

CREATE TABLE IF NOT EXISTS Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(20) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    contrasenia VARCHAR(100) NOT NULL,
    estado BIT NOT NULL DEFAULT 1,
    telefono VARCHAR(20) NOT NULL,    
    direccion VARCHAR(255) NOT NULL
    -- creation_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora de creacion del cliente en la bd'
);

CREATE TABLE IF NOT EXISTS Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(20) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    contrasenia VARCHAR(100) NOT NULL,
    estado BIT NOT NULL DEFAULT 1,
    telefono VARCHAR(20) NOT NULL,    
    direccion VARCHAR(255) NOT NULL
    -- creation_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora de creacion del cliente en la bd'
);

CREATE TABLE report_customer_movements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    cliente VARCHAR(100) NOT NULL,
    numero_cuenta VARCHAR(20) NOT NULL,
    tipo VARCHAR(20) NOT NULL, -- AHORRO / CORRIENTE
    saldo_inicial DECIMAL(12,2) NOT NULL,
    estado BOOLEAN NOT NULL,
    valor_movimiento DECIMAL(12,2) NOT NULL,
    tipo_movimiento VARCHAR(20) NOT NULL, -- CREDITO / DEBITO
    saldo_disponible DECIMAL(12,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,    
    INDEX idx_cliente (cliente),    
    INDEX idx_numero_cuenta (numero_cuenta)
);

-- =====================================
-- TABLA: ACCOUNTS
-- =====================================
CREATE TABLE Accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(20) NOT NULL UNIQUE,
    tipo ENUM('AHORRO', 'CORRIENTE') NOT NULL,
    saldo_inicial DECIMAL(12,2) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    cliente_nombre VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Índice para búsquedas por cliente
CREATE INDEX idx_accounts_cliente ON Accounts(cliente_nombre);


-- =====================================
-- TABLA: MOVEMENTS
-- =====================================
CREATE TABLE Movements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(20) NOT NULL,
    tipo_cuenta ENUM('AHORRO', 'CORRIENTE') NOT NULL,
    saldo_inicial DECIMAL(12,2) NOT NULL,
    valor DECIMAL(12,2) NOT NULL,
    tipo_movimiento ENUM('CREDITO', 'DEBITO') NOT NULL,
    saldo DECIMAL(12,2) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Relación con accounts
    CONSTRAINT fk_movements_account
        FOREIGN KEY (numero_cuenta)
        REFERENCES Accounts(numero_cuenta)
        ON DELETE CASCADE
);

-- Índices para performance
CREATE INDEX idx_movements_numero_cuenta ON Movements(numero_cuenta);
CREATE INDEX idx_movements_fecha ON Movements(created_at);

USE pichincha_interv;
DROP TABLE Cliente;
DROP TABLE report_customer_movements;
DROP TABLE Accounts;
DROP TABLE Movements;

SELECT * FROM Cliente;
SELECT * FROM Accounts;
SELECT * FROM Movements;

SELECT * FROM report_customer_movements;

INSERT INTO Cliente (nombre,genero, dni, contrasenia, estado, telefono, direccion) VALUES
('Jose Lema','Masculino', '098254785', '1234', b'1', '098254785', 'Otavalo sn y principal'),
('Marianela Montalvo','Femenino', '097548965', '5678', b'1', '097548965', 'Amazonas y NNUU'),
('Juan Osorio','Masculino', '098874587', '1245', b'1', '098874587', '13 junio y Equinoccial');

DELETE FROM Cliente where id = 4;
COMMIT;

DELETE FROM Accounts where id >2;
COMMIT;

DELETE FROM Movements where id >2;
COMMIT;