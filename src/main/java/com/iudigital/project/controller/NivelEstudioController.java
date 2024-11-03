package com.iudigital.project.controller;
import com.iudigital.project.dao.NivelEstudioDao;
import com.iudigital.project.domain.NivelEstudio;
import java.sql.SQLException;
import java.util.List;


public class NivelEstudioController {
    
    private NivelEstudioDao nivelEstudioDao;
    
    public NivelEstudioController() {
        nivelEstudioDao = new NivelEstudioDao();
    }
    
    public List<NivelEstudio> getNivelEstudio() throws SQLException {
        return nivelEstudioDao.getNivelesEstidio();
    }
    
}
