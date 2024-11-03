package com.iudigital.project.controller;
import com.iudigital.project.dao.SexoDao;
import com.iudigital.project.domain.Sexo;
import java.sql.SQLException;
import java.util.List;


public class SexoController {
    
    private SexoDao sexoDao;
    
    public SexoController() {
        sexoDao = new SexoDao();
    }
    
    public List<Sexo> getSexos() throws SQLException {
        return sexoDao.getSexos();
    }
}
