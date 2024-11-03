package com.iudigital.project.controller;
import com.iudigital.project.dao.GrupoFamiliarDao;
import com.iudigital.project.domain.GrupoFamiliar;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class GrupoFamiliarController {

    private GrupoFamiliarDao grupoFamiliarDao;

    public GrupoFamiliarController() {
        grupoFamiliarDao = new GrupoFamiliarDao();
    }

    
    public List<GrupoFamiliar> getGruposFamiliares() throws SQLException {
        return grupoFamiliarDao.getGruposFamiliares();
    }

    
    public void create(GrupoFamiliar grupoFamiliar) throws SQLException {
        grupoFamiliar.setFechaCreacion(LocalDateTime.now());
        grupoFamiliarDao.createGrupoFamiliar(grupoFamiliar);
    }

    
    public GrupoFamiliar getGrupoFamiliar(int id) throws SQLException {
        return grupoFamiliarDao.getGrupoFamiliarById(id);
    }

    
    public void updateGrupoFamiliar(int id, GrupoFamiliar grupoFamiliar) throws SQLException {
        grupoFamiliar.setFechaActualizacion(LocalDateTime.now());
        grupoFamiliarDao.updateGrupoFamiliar(id, grupoFamiliar);
    }

    
    public void deleteGrupoFamiliar(int id) throws SQLException {
        grupoFamiliarDao.deleteGrupoFamiliar(id);
    }
}

