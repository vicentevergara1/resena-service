package cl.duoc.fullstack.resena.service;

import cl.duoc.fullstack.resena.dto.ResenaRequest;
import cl.duoc.fullstack.resena.model.Resena;
import cl.duoc.fullstack.resena.repository.ResenaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @InjectMocks
    private ResenaService resenaService;

    @Test
    void crearResena_DeberiaGuardarYRetornarResena() {
        ResenaRequest request = new ResenaRequest();
        request.setProductoId(1L);
        request.setCalificacion(5);
        request.setComentario("¡Excelente producto!");
        Resena resenaSimulada = Resena.builder()
                .id(100L)
                .productoId(1L)
                .calificacion(5)
                .comentario("¡Excelente producto!")
                .fechaCreacion(LocalDateTime.now())
                .build();
        when(resenaRepository.save(any(Resena.class))).thenReturn(resenaSimulada);
        Resena resultado = resenaService.crearResena(request);
        assertNotNull(resultado);
        assertEquals(100L, resultado.getId());
        assertEquals(1L, resultado.getProductoId());
        assertEquals(5, resultado.getCalificacion());
        assertEquals("¡Excelente producto!", resultado.getComentario());
        verify(resenaRepository, times(1)).save(any(Resena.class));
    }

    @Test
    void obtenerResenasPorProducto_DeberiaRetornarListaDeResenas() {
        Long productoId = 1L;
        Resena resena1 = Resena.builder().id(1L).productoId(productoId).calificacion(4).comentario("Bueno").build();
        Resena resena2 = Resena.builder().id(2L).productoId(productoId).calificacion(5).comentario("Genial").build();
        when(resenaRepository.findByProductoId(productoId)).thenReturn(List.of(resena1, resena2));
        List<Resena> resultado = resenaService.obtenerResenasPorProducto(productoId);
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(productoId, resultado.get(0).getProductoId());
        verify(resenaRepository, times(1)).findByProductoId(productoId);
    }
}