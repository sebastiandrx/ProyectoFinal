package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.Equipo;
import com.example.ProyectoFinal.Repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoRepository equipoRepo;


    @PostMapping
    public Equipo createEquipo(@RequestBody Equipo equipo) {
        return equipoRepo.save(equipo);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Equipo> getEquiposPorUsuario(@PathVariable UUID usuarioId) {
        return equipoRepo.findByUsuarioId(usuarioId);
    }

    @GetMapping
    public List<Equipo> getAllEquipos() {
        return equipoRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable UUID id) {
        return equipoRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable UUID id, @RequestBody Equipo actualizado) {
        return equipoRepo.findById(id)
                .map(equipo -> {
                    equipo.setMarca(actualizado.getMarca());
                    equipo.setModelo(actualizado.getModelo());
                    equipo.setSerial(actualizado.getSerial());
                    equipo.setFotoUrl(actualizado.getFotoUrl());
                    equipo.setUsuario(actualizado.getUsuario());
                    return ResponseEntity.ok(equipoRepo.save(equipo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable UUID id) {
        return equipoRepo.findById(id)
                .map(equipo -> {
                    equipoRepo.delete(equipo);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}