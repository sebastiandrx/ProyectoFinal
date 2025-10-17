package com.example.ProyectoFinal.Service;

import com.example.ProyectoFinal.Models.Historial;
import com.example.ProyectoFinal.Repository.HistorialRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class HistorialService {

    private final HistorialRepository historialRepository;

    public HistorialService(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public List<Historial> obtenerHistorialPorUsuario(UUID usuarioId) {
        return historialRepository.findByUsuarioId(usuarioId);
    }

    public List<Historial> obtenerHistorialPorTipo(UUID usuarioId, String tipo) {
        return historialRepository.findByUsuarioIdAndTipoMovimiento(usuarioId, tipo);
    }

    public Historial registrarMovimiento(Historial historial) {
        historial.setFechaHora(java.time.LocalDateTime.now());
        return historialRepository.save(historial);
    }
}
