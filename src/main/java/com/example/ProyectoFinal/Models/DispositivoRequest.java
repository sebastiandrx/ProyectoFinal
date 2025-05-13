package com.example.ProyectoFinal.Models;

import java.util.UUID;

public class DispositivoRequest {
    private String marca;
    private String modelo;
    private String serial;
    private String fotoUrl;
    private UUID usuario_id;

    // Getters y Setters

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public UUID getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(UUID usuario_id) {
        this.usuario_id = usuario_id;
    }
}

