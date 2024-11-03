package com.iudigital.project.controller;
import com.iudigital.project.dao.EstadoCivilDao;
import com.iudigital.project.domain.EstadoCivil;
import java.sql.SQLException;
import java.util.List;

public class EstadoCivilController {
    
    private EstadoCivilDao estadoCivilDao;
    
    public EstadoCivilController() {
        estadoCivilDao = new EstadoCivilDao();
    }
    
    public List<EstadoCivil> getEstadosCiviles() throws SQLException {
        return estadoCivilDao.getEstadosCiviles();
    }
}
