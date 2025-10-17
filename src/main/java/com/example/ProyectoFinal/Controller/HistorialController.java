package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.Historial;
import com.example.ProyectoFinal.Service.HistorialService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    private final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @GetMapping("/{usuarioId}")
    public List<Historial> obtenerHistorialPorUsuario(@PathVariable UUID usuarioId) {
        return historialService.obtenerHistorialPorUsuario(usuarioId);
    }

    @GetMapping("/{usuarioId}/tipo/{tipo}")
    public List<Historial> obtenerHistorialPorTipo(
            @PathVariable UUID usuarioId,
            @PathVariable String tipo
    ) {
        return historialService.obtenerHistorialPorTipo(usuarioId, tipo);
    }

    @PostMapping
    public Historial registrarMovimiento(@RequestBody Historial historial) {
        return historialService.registrarMovimiento(historial);
    }
}
