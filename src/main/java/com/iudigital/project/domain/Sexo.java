package com.iudigital.project.domain;

import java.util.Objects;

public class Sexo {

    private int id;
    private String sexo;

    public Sexo() {
    }

    public Sexo(int id) {
        this.id = id;
    }

    public Sexo(int id, String sexo) {
        this.id = id;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return sexo;
    }
    
    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sexo that = (Sexo) o;
    return id == that.id && Objects.equals(sexo, that.sexo);
}

    @Override
    public int hashCode() {
        return Objects.hash(id, sexo);
    }

    public void addItem(Sexo sexouno) {
        throw new UnsupportedOperationException();
    }
}
