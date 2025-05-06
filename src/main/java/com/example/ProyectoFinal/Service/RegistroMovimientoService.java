package com.example.ProyectoFinal.Service;

import com.example.ProyectoFinal.Models.RegistroMovimiento;
import com.example.ProyectoFinal.Repository.RegistroMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegistroMovimientoService {

    @Autowired
    private RegistroMovimientoRepository movimientoRepository;

    public List<RegistroMovimiento> findByUsuario(UUID usuarioId) {
        return movimientoRepository.findByUsuarioId(usuarioId);
    }

    public RegistroMovimiento save(RegistroMovimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }
}

