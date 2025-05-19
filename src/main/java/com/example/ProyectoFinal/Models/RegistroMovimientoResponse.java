package com.example.ProyectoFinal.Models;

public class RegistroMovimientoResponse {
    private String nombre;
    private String documento;
    private String equipo;
    private String carrera;
    private String fechaHora;
    private String tipoMovimiento;

    // Constructor
    public RegistroMovimientoResponse(String nombre, String documento, String equipo, String carrera, String fechaHora, String tipoMovimiento) {
        this.nombre = nombre;
        this.documento = documento;
        this.equipo = equipo;
        this.carrera = carrera;
        this.fechaHora = fechaHora;
        this.tipoMovimiento = tipoMovimiento;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEquipo() {
        return equipo;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
}
