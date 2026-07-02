package cl.duoc.fullstack.resena.service;

import cl.duoc.fullstack.resena.dto.ResenaRequest;
import cl.duoc.fullstack.resena.model.Resena;
import cl.duoc.fullstack.resena.repository.ResenaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResenaService {

    private final ResenaRepository resenaRepository;

    public Resena crearResena(ResenaRequest request) {
        Resena resena = Resena.builder()
                .productoId(request.getProductoId())
                .calificacion(request.getCalificacion())
                .comentario(request.getComentario())
                .build();
        return resenaRepository.save(resena);
    }

    public List<Resena> obtenerResenasPorProducto(Long productoId) {
        return resenaRepository.findByProductoId(productoId);
    }
}