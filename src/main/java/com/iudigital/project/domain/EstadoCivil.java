package com.iudigital.project.domain;

import java.util.Objects;

public class EstadoCivil {

    private int id;
    private String estadoCivil;

    public EstadoCivil() {
    }

    public EstadoCivil(int id) {
        this.id = id;
    }

    public EstadoCivil(int id, String estadoCivil) {
        this.id = id;
        this.estadoCivil = estadoCivil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public String toString() {
        return estadoCivil;
    }
    
    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EstadoCivil that = (EstadoCivil) o;
    return id == that.id && Objects.equals(estadoCivil, that.estadoCivil);
}

    @Override
    public int hashCode() {
        return Objects.hash(id, estadoCivil);
    }

    public void addItem(EstadoCivil estadocivil) {
        throw new UnsupportedOperationException();
    }

    
}
