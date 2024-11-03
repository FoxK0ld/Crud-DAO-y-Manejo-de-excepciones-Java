package com.iudigital.project.domain;

import java.time.LocalDateTime;

public class InformacionAcademica {

    private int idInformacionAcademica;
    private int idFuncionario;
    private int idUniversidad;
    private int idNivelEstudio;
    private String titulo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    
    public InformacionAcademica() {
    }

    
    public InformacionAcademica(int idInformacionAcademica) {
        this.idInformacionAcademica = idInformacionAcademica;
    }

    

    public int getIdInformacionAcademica() {
        return idInformacionAcademica;
    }

    public void setIdInformacionAcademica(int idInformacionAcademica) {
        this.idInformacionAcademica = idInformacionAcademica;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public int getIdNivelEstudio() {
        return idNivelEstudio;
    }

    public void setIdNivelEstudio(int idNivelEstudio) {
        this.idNivelEstudio = idNivelEstudio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
        return titulo;
    }
}

