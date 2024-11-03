package com.iudigital.project.controller;
import com.iudigital.project.dao.UniversidadDao;
import com.iudigital.project.domain.Universidad;
import java.sql.SQLException;
import java.util.List;


public class UniversidadController {
    
    private UniversidadDao universidadDao;
    
    public UniversidadController() {
        universidadDao = new UniversidadDao();
    }
    
    public List<Universidad> getUniversidades() throws SQLException {
        return universidadDao.getUniversidades();
    }
    
}
