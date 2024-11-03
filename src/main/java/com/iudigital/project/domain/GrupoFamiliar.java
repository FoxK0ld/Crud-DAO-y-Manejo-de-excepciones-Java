package com.iudigital.project.domain;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GrupoFamiliar {

    private int idGrupoFamiliar;
    private int idFuncionario;
    private String nombre;
    private String apellido;
    private String parentesco;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public GrupoFamiliar() {
    }

    public GrupoFamiliar(int idGrupoFamiliar) {
        this.idGrupoFamiliar = idGrupoFamiliar;
    }

    public int getIdGrupoFamiliar() {
        return idGrupoFamiliar;
    }

    public void setIdGrupoFamiliar(int idGrupoFamiliar) {
        this.idGrupoFamiliar = idGrupoFamiliar;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public String toString() {
        return nombre + apellido;
    }
    
    
    

}