package com.example.ProyectoFinal.Repository;

import com.example.ProyectoFinal.Models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, UUID> {
    List<Equipo> findByUsuarioId(UUID usuarioId);
}
