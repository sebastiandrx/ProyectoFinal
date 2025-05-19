package com.example.ProyectoFinal.Models;

import java.util.UUID;

public class MovimientoQrRequest {
    private UUID usuarioId;
    private UUID equipoId;
    private TipoMovimiento tipoMovimiento;

    // Constructor vacío (necesario para deserialización JSON)
    public MovimientoQrRequest() {
    }

    // Getters y setters
    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UUID getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(UUID equipoId) {
        this.equipoId = equipoId;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
}
