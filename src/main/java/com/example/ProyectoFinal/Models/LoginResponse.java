package com.example.ProyectoFinal.Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class LoginResponse {

    private UUID usuarioId;
    private String rol;
    private String token;
    private LocalDateTime expiracion;

    public LoginResponse(UUID usuarioId, String rol, String token, LocalDateTime expiracion) {
        this.usuarioId = usuarioId;
        this.rol = rol;
        this.token = token;
        this.expiracion = expiracion;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(LocalDateTime expiracion) {
        this.expiracion = expiracion;
    }


}
