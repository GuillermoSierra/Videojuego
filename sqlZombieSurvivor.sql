DROP DATABASE IF EXISTS ZombieSurvivor;

CREATE DATABASE IF NOT EXISTS ZombieSurvivor;

USE ZombieSurvivor;

DROP TABLE IF EXISTS partidas;
CREATE TABLE IF NOT EXISTS partidas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_jugador VARCHAR(100) NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME DEFAULT NULL,
    duracion_sec BIGINT DEFAULT NULL,
    num_movimiento INT DEFAULT 0,
    victoria BOOLEAN DEFAULT FALSE
);