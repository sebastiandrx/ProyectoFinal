package com.example.ProyectoFinal.Models;

import java.util.UUID;

public class MovimientoRequest {
    private UUID usuarioId;
    private UUID equipoId;
    private UUID guardiaId;
    private TipoMovimiento tipoMovimiento;


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

    public UUID getGuardiaId() {
        return guardiaId;
    }

    public void setGuardiaId(UUID guardiaId) {
        this.guardiaId = guardiaId;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
}
