package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.*;
import com.example.ProyectoFinal.Repository.TokenLoginRepository;
import com.example.ProyectoFinal.Service.RegistroMovimientoService;
import com.example.ProyectoFinal.Service.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/movimientos")
@SecurityRequirement(name = "bearerAuth")
public class RegistroMovimientoController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private TokenLoginRepository tokenRepo;

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

        Usuario guardia = tokenRepo.findByToken(token).get().getUsuario();
        if (!guardia.getRol().equals(Rol.GUARDIA)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo el guardia puede registrar movimientos");
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

    @GetMapping("/{id}")
    public ResponseEntity<RegistroMovimiento> obtenerPorId(@PathVariable UUID id) {
        return movimientoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable UUID id) {
        if (movimientoService.eliminarPorId(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<RegistroMovimiento>> filtrarPorFechas(
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta) {
        List<RegistroMovimiento> resultados = movimientoService.filtrarPorFechas(desde, hasta);
        return ResponseEntity.ok(resultados);
    }


    @PostMapping("/escanear-qr")
    
    public ResponseEntity<?> escanearQr(@RequestHeader("Authorization") String authHeader,
                                        @RequestBody Map<String, String> datos) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }

        String token = authHeader.substring(7);
        Usuario guardia = tokenRepo.findByToken(token)
                .map(TokenLogin::getUsuario)
                .orElse(null);

        if (guardia == null || !Rol.GUARDIA.name().equalsIgnoreCase(guardia.getRol())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solo el guardia puede registrar movimientos");
        }

        try {
            // Extraer datos del body JSON
            UUID usuarioId = UUID.fromString(datos.get("usuarioId"));
            UUID equipoId = UUID.fromString(datos.get("equipoId"));
            TipoMovimiento tipo = TipoMovimiento.valueOf(datos.get("tipoMovimiento").toUpperCase());

            // Crear el request
            MovimientoRequest request = new MovimientoRequest();
            request.setUsuarioId(usuarioId);
            request.setEquipoId(equipoId);
            request.setGuardiaId(guardia.getId());
            request.setTipoMovimiento(tipo);

            // Ejecutar y construir la respuesta
            RegistroMovimiento nuevo = movimientoService.registrarMovimiento(request);
            Usuario usuario = nuevo.getUsuario();

            RegistroMovimientoResponse response = new RegistroMovimientoResponse(
                    usuario.getNombre(),
                    usuario.getDocumento(),
                    nuevo.getEquipo().getMarca() + " - " + nuevo.getEquipo().getModelo(),
                    usuario.getCarrera(),
                    nuevo.getFechaHora().toString(),
                    nuevo.getTipoMovimiento().name()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar QR: " + e.getMessage());
        }
    }

}

