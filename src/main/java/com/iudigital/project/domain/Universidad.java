
package com.iudigital.project.domain;

import java.util.Objects;


public class Universidad {
    
    private int id;
    private String tipo;

    public Universidad() {
        
    }
    
     public Universidad(int id) {
        this.id = id;
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
    Universidad that = (Universidad) o;
    return id == that.id && Objects.equals(tipo, that.tipo);
}

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo);
    }
    
     public void addItem(Universidad universidaduno) {
        throw new UnsupportedOperationException();
    }
     
}
