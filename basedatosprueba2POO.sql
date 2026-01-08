use railway;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    cedula VARCHAR(20) NOT NULL UNIQUE,
    contrasena_encryp VARCHAR(100) NOT NULL,
    rol ENUM("ADMIN","ANALISTA") NOT NULL,
    estado ENUM("ACTIVO","INACTIVO") NOT NULL DEFAULT 'ACTIVO' 
);

CREATE TABLE solicitante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL
);

CREATE TABLE tramite (
    id INT AUTO_INCREMENT PRIMARY KEY,
    solicitante_id INT NOT NULL,
    estado ENUM('PENDIENTE','EN_EXAMENES','APROBADO','REPROBADO','LICENCIA_EMITIDA') DEFAULT 'PENDIENTE',
    tipoLicencia VARCHAR(4) DEFAULT "A",
    fechaSolicitud DATETIME DEFAULT CURRENT_TIMESTAMP,
    --  PARA LOS CHECKBOXES
    certificadoMedico BOOLEAN DEFAULT FALSE,
    pago BOOLEAN DEFAULT FALSE,
    sinMultas BOOLEAN DEFAULT FALSE,
    observaciones TEXT,
    -- FIN CHECKBOXES
    creado_por INT,
    creado_en DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (solicitante_id) REFERENCES solicitante(id),
    FOREIGN KEY (creado_por) REFERENCES usuario(id)
);

CREATE TABLE examen (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tramite_id INT UNIQUE NOT NULL,
    nota_teorica DECIMAL(4,2),
    nota_practica DECIMAL(4,2),
    fecha_examen DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tramite_id) REFERENCES tramite(id)
);

CREATE TABLE licencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tramite_id INT UNIQUE NOT NULL,
    numero_licencia VARCHAR(50) UNIQUE NOT NULL,
    fecha_emision DATETIME NOT NULL,
    fecha_vencimiento DATETIME NOT NULL,
    creado_por INT,
    creado_en DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tramite_id) REFERENCES tramite(id),
    FOREIGN KEY (creado_por) REFERENCES usuario(id)
);

-- DELIMITER //
-- CREATE PROCEDURE sp_total_licencias_por_fecha(IN p_fecha DATE, OUT p_total INT)
-- BEGIN
    -- SELECT COUNT(*) INTO p_total
    -- FROM licencia
    -- WHERE fecha_emision = p_fecha;
-- END //
-- DELIMITER ;
