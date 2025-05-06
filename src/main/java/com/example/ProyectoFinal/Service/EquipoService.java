package com.example.ProyectoFinal.Service;

import com.example.ProyectoFinal.Models.Equipo;
import com.example.ProyectoFinal.Repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> findByUsuarioId(UUID usuarioId) {
        return equipoRepository.findByUsuarioId(usuarioId);
    }

    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }
}
