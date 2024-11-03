
package com.iudigital.project.domain;


public class NivelEstudio {
    
    private int id;
    private String nivelEstudio;

    public NivelEstudio() {
        
    }
    
    public NivelEstudio(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    @Override
    public String toString() {
        return nivelEstudio;
    }
    
    
    
    
    
}
