package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.DispositivoRequest;
import com.example.ProyectoFinal.Models.Equipo;
import com.example.ProyectoFinal.Models.Usuario;
import com.example.ProyectoFinal.Repository.EquipoRepository;
import com.example.ProyectoFinal.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoRepository equipoRepo;
    @Autowired
    private UsuarioRepository usuarioRepo;


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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable UUID id) {
        return equipoRepo.findById(id)
                .map(equipo -> {
                    equipoRepo.delete(equipo);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> registrarEquipo(@RequestBody DispositivoRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepo.findById(request.getUsuario_id());

        if (!usuarioOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        Equipo equipo = new Equipo();
        equipo.setMarca(request.getMarca());
        equipo.setModelo(request.getModelo());
        equipo.setSerial(request.getSerial());
        equipo.setFotoUrl(request.getFotoUrl());
        equipo.setUsuario(usuario); // 👈 AQUÍ VINCULAS CORRECTAMENTE

        Equipo guardado = equipoRepo.save(equipo);

        return ResponseEntity.ok(guardado);
    }

    // 🔹 PUT para actualizar los datos del equipo
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEquipo(
            @PathVariable UUID id,
            @RequestBody DispositivoRequest request) {

        Optional<Equipo> optionalEquipo = equipoRepo.findById(id);

        if (!optionalEquipo.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Equipo equipoExistente = optionalEquipo.get();

        equipoExistente.setMarca(request.getMarca());
        equipoExistente.setModelo(request.getModelo());
        equipoExistente.setSerial(request.getSerial());

        if (request.getFotoUrl() != null && !request.getFotoUrl().isBlank()) {
            equipoExistente.setFotoUrl(request.getFotoUrl());
        }

        Equipo actualizado = equipoRepo.save(equipoExistente);
        return ResponseEntity.ok(actualizado);
    }
}
