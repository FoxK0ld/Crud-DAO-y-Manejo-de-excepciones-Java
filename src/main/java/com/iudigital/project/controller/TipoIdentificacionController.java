package com.iudigital.project.controller;
import com.iudigital.project.dao.TipoIdentificacionDao;
import com.iudigital.project.domain.TipoIdentificacion;
import java.sql.SQLException;
import java.util.List;


public class TipoIdentificacionController {
    
    private TipoIdentificacionDao tipoIdentificacionDao;
    
    public TipoIdentificacionController() {
        tipoIdentificacionDao = new TipoIdentificacionDao();
    }
    
    public List<TipoIdentificacion> getTiposIdentificacion() throws SQLException {
        return tipoIdentificacionDao.getTiposIdentificacion();
    }
}
