package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.MovimientoRequest;
import com.example.ProyectoFinal.Models.RegistroMovimiento;
import com.example.ProyectoFinal.Models.TokenService;
import com.example.ProyectoFinal.Service.RegistroMovimientoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movimientos")
@SecurityRequirement(name = "bearerAuth")
public class RegistroMovimientoController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RegistroMovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestHeader("Authorization") String authHeader,
                                       @RequestBody MovimientoRequest request) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o ausente");
        }

        String token = authHeader.substring(7);

        if (!tokenService.tokenValido(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no autorizado");
        }

        RegistroMovimiento nuevo = movimientoService.registrarMovimiento(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<RegistroMovimiento>> historialPorUsuario(@PathVariable UUID id) {
        return ResponseEntity.ok(movimientoService.historialPorUsuario(id));
    }

    @GetMapping
    public ResponseEntity<List<RegistroMovimiento>> historialGeneral() {
        return ResponseEntity.ok(movimientoService.historialGeneral());
    }
}


