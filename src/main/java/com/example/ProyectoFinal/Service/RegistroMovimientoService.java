package com.example.ProyectoFinal.Service;

import com.example.ProyectoFinal.Models.Equipo;
import com.example.ProyectoFinal.Models.MovimientoRequest;
import com.example.ProyectoFinal.Models.RegistroMovimiento;
import com.example.ProyectoFinal.Models.Usuario;
import com.example.ProyectoFinal.Repository.EquipoRepository;
import com.example.ProyectoFinal.Repository.RegistroMovimientoRepository;
import com.example.ProyectoFinal.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

        RegistroMovimiento movimiento = new RegistroMovimiento();
        movimiento.setUsuario(usuario);
        movimiento.setEquipo(equipo);
        movimiento.setGuardia(guardia);
        movimiento.setTipoMovimiento(request.getTipoMovimiento());
        movimiento.setFechaHora(LocalDateTime.now());

        return registroRepo.save(movimiento);
    }

    public List<RegistroMovimiento> historialPorUsuario(UUID id) {
        return registroRepo.findByUsuarioId(id);
    }

    public List<RegistroMovimiento> historialGeneral() {
        return registroRepo.findAll();
    }
}
