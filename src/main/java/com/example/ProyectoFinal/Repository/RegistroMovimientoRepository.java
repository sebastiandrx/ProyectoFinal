package com.example.ProyectoFinal.Repository;

import com.example.ProyectoFinal.Models.RegistroMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RegistroMovimientoRepository extends JpaRepository<RegistroMovimiento, UUID> {
    List<RegistroMovimiento> findByUsuarioId(UUID usuarioId);
    List<RegistroMovimiento> findByEquipoId(UUID equipoId);
    List<RegistroMovimiento> findByFechaHoraBetween(LocalDateTime desde, LocalDateTime hasta);

}
