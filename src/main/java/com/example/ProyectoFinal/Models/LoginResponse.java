package com.example.ProyectoFinal.Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class LoginResponse {

    private UUID usuarioId;
    private String nombre;
    private String documento;
    private String correo;
    private String carrera;
    private String rol;
    private String token;
    private LocalDateTime expiracion;

    public LoginResponse(UUID usuarioId, String nombre, String documento, String correo, String carrera, String rol, String token, LocalDateTime expiracion) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
        this.carrera = carrera;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
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

