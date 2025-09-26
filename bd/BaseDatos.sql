CREATE DATABASE BancosClientes;
USE BancosClientes;

CREATE TABLE Clientes (
    id BIGINT IDENTITY PRIMARY KEY,
    cliente_id VARCHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(100),
    identificacion VARCHAR(20),
    telefono VARCHAR(20),
    direccion VARCHAR(150),
    edad INT,
    genero CHAR(1),
    contrasena VARCHAR(50),
    activo BIT
);


CREATE DATABASE BancosCuentas;
USE BancosCuentas;

CREATE TABLE Cuentas (
    id BIGINT IDENTITY PRIMARY KEY,
    numero_cuenta VARCHAR(20) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(50),
    saldo_inicial DECIMAL(18,2),
    saldo_actual DECIMAL(18,2),
    estado BIT,
    cliente_id VARCHAR(10) 
);

CREATE TABLE Movimientos (
    id BIGINT IDENTITY PRIMARY KEY,
    fecha DATETIME,
    tipo_movimiento VARCHAR(20),
    valor DECIMAL(18,2),
    saldo_disponible DECIMAL(18,2),
    cuenta_id BIGINT,
    FOREIGN KEY (cuenta_id) REFERENCES Cuentas(id)
);
