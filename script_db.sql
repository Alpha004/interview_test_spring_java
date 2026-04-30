USE pichincha_interv;

CREATE TABLE IF NOT EXISTS Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(20) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    contrasenia VARCHAR(100) NOT NULL,
    estado BIT NOT NULL DEFAULT 1,
    telefono VARCHAR(20) NOT NULL,    
    direccion VARCHAR(255) NOT NULL,
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora de creacion del cliente en la bd'
);

DROP TABLE Cliente;

SELECT * FROM Cliente;

INSERT INTO Cliente (nombre,genero, dni, contrasenia, estado, telefono, direccion) VALUES
('Jose Lema','Masculino', '098254785', '1234', b'1', '098254785', 'Otavalo sn y principal'),
('Marianela Montalvo','Femenino', '097548965', '5678', b'1', '097548965', 'Amazonas y NNUU'),
('Juan Osorio','Masculino', '098874587', '1245', b'1', '098874587', '13 junio y Equinoccial');

DELETE FROM Cliente where id = 4;
COMMIT;