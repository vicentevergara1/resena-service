CREATE TABLE resenas (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         producto_id BIGINT NOT NULL,
                         calificacion INT NOT NULL CHECK (calificacion >= 1 AND calificacion <= 5),
                         comentario VARCHAR(500) NOT NULL,
                         fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);