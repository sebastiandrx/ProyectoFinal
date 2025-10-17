package com.example.ProyectoFinal.Repository;

import com.example.ProyectoFinal.Models.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, UUID> {
    List<Historial> findByUsuarioId(UUID usuarioId);
    List<Historial> findByUsuarioIdAndTipoMovimiento(UUID usuarioId, String tipoMovimiento);
}
