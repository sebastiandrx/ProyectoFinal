package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.RegistroMovimiento;
import com.example.ProyectoFinal.Service.RegistroMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movimientos")
public class RegistroMovimientoController {

    @Autowired
    private RegistroMovimientoService movimientoService;

    @GetMapping("/usuario/{usuarioId}")
    public List<RegistroMovimiento> getMovimientosUsuario(@PathVariable UUID usuarioId) {
        return movimientoService.findByUsuario(usuarioId);
    }

    @PostMapping
    public ResponseEntity<RegistroMovimiento> registrarMovimiento(@RequestBody RegistroMovimiento mov) {
        return new ResponseEntity<>(movimientoService.save(mov), HttpStatus.CREATED);
    }
}

