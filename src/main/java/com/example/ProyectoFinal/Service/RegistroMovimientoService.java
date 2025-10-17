package com.example.ProyectoFinal.Service;

import com.example.ProyectoFinal.Models.*;
import com.example.ProyectoFinal.Repository.EquipoRepository;
import com.example.ProyectoFinal.Repository.RegistroMovimientoRepository;
import com.example.ProyectoFinal.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistroMovimientoService {

    @Autowired
    private RegistroMovimientoRepository registroRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private EquipoRepository equipoRepo;

    public RegistroMovimiento registrarMovimiento(MovimientoRequest request) {
        Usuario usuario = usuarioRepo.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Equipo equipo = equipoRepo.findById(request.getEquipoId())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        Usuario guardia = usuarioRepo.findById(request.getGuardiaId())
                .orElseThrow(() -> new RuntimeException("Guardia no encontrado"));

        // 🟢 Crear el nuevo registro del movimiento
        RegistroMovimiento movimiento = new RegistroMovimiento();
        movimiento.setUsuario(usuario);
        movimiento.setEquipo(equipo);
        movimiento.setGuardia(guardia);
        movimiento.setTipoMovimiento(request.getTipoMovimiento());

        // 🟢 Establecer la fecha y hora actual del servidor
        movimiento.setFechaHora(LocalDateTime.now());

        // 🟢 Guardar el registro en la base de datos
        RegistroMovimiento guardado = registroRepo.save(movimiento);

        System.out.println("✅ Movimiento registrado con fecha: " + guardado.getFechaHora());
        return guardado;
    }


    public List<RegistroMovimiento> historialPorUsuario(UUID id) {
        return registroRepo.findByUsuarioId(id);
    }

    public List<RegistroMovimiento> historialGeneral() {
        return registroRepo.findAll();
    }

    public Optional<RegistroMovimiento> obtenerPorId(UUID id) {
        return registroRepo.findById(id);
    }

    public boolean eliminarPorId(UUID id) {
        if (registroRepo.existsById(id)) {
            registroRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<RegistroMovimiento> filtrarPorFechas(String desde, String hasta) {
        LocalDateTime fechaDesde = LocalDateTime.parse(desde);
        LocalDateTime fechaHasta = LocalDateTime.parse(hasta);
        return registroRepo.findByFechaHoraBetween(fechaDesde, fechaHasta);
    }

    public TipoMovimiento determinarTipoMovimiento(UUID usuarioId, UUID equipoId) {
        // Buscar el último movimiento de ese usuario y equipo
        List<RegistroMovimiento> historial = registroRepo
                .findByUsuarioIdAndEquipoIdOrderByFechaHoraDesc(usuarioId, equipoId);

        if (historial.isEmpty()) {
            return TipoMovimiento.ENTRADA; // Si no hay registro, inicia con ENTRADA
        }

        RegistroMovimiento ultimo = historial.get(0);
        return ultimo.getTipoMovimiento() == TipoMovimiento.ENTRADA
                ? TipoMovimiento.SALIDA
                : TipoMovimiento.ENTRADA;
    }

}
