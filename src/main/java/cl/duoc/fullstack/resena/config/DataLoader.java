package cl.duoc.fullstack.resena.config;

import cl.duoc.fullstack.resena.model.Resena;
import cl.duoc.fullstack.resena.repository.ResenaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final ResenaRepository resenaRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Verificando si existen datos iniciales en resena-service...");

        if (resenaRepository.count() == 0) {
            log.info("Cargando datos de prueba para Reseñas...");

            resenaRepository.save(Resena.builder()
                    .productoId(1L)
                    .calificacion(5)
                    .comentario("¡Excelente producto! Superó mis expectativas.")
                    .build());

            resenaRepository.save(Resena.builder()
                    .productoId(1L)
                    .calificacion(4)
                    .comentario("Muy bueno, pero el envío tardó un poco.")
                    .build());

            resenaRepository.save(Resena.builder()
                    .productoId(2L)
                    .calificacion(3)
                    .comentario("Cumple su función, pero la calidad podría ser mejor.")
                    .build());

            resenaRepository.save(Resena.builder()
                    .productoId(3L)
                    .calificacion(5)
                    .comentario("Totalmente recomendado, volvería a comprar.")
                    .build());

            log.info("Datos de prueba cargados exitosamente.");
        } else {
            log.info("La base de datos ya contiene reseñas. No se requiere carga inicial.");
        }
    }
}