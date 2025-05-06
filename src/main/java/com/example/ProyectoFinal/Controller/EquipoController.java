package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.Equipo;
import com.example.ProyectoFinal.Service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/usuario/{usuarioId}")
    public List<Equipo> getEquiposUsuario(@PathVariable UUID usuarioId) {
        return equipoService.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        return new ResponseEntity<>(equipoService.save(equipo), HttpStatus.CREATED);
    }
}

