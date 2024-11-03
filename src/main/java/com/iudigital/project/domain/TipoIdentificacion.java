package com.iudigital.project.domain;

import java.util.Objects;

public class TipoIdentificacion {

    private int id;
    private String tipo;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(int id) {
        this.id = id;
    }

    public TipoIdentificacion(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
    
    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TipoIdentificacion that = (TipoIdentificacion) o;
    return id == that.id && Objects.equals(tipo, that.tipo);
}

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }

    
    public void addItem(TipoIdentificacion tipoidentificacion) {
        throw new UnsupportedOperationException();
    }
}



    
    
    
    



