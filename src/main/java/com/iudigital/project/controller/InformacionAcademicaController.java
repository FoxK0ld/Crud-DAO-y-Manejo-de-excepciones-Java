package com.iudigital.project.controller;
import com.iudigital.project.dao.InformacionAcademicaDao;
import com.iudigital.project.domain.InformacionAcademica;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class InformacionAcademicaController {

    private InformacionAcademicaDao informacionAcademicaDao;

    public InformacionAcademicaController() {
        informacionAcademicaDao = new InformacionAcademicaDao();
    }

    // Obtener todas las informaciones académicas
    public List<InformacionAcademica> getInformacionesAcademica() throws SQLException {
        return informacionAcademicaDao.getInformacionesAcademica();
    }

    // Crear una nueva información académica
    public void create(InformacionAcademica infoAcademica) throws SQLException {
        infoAcademica.setFechaCreacion(LocalDateTime.now());
        informacionAcademicaDao.createInformacionAcademica(infoAcademica);
    }

    // Obtener una información académica por ID
    public InformacionAcademica getInformacionAcademica(int id) throws SQLException {
        return informacionAcademicaDao.getInformacionAcademicaById(id);
    }

    // Actualizar una información académica
    public void updateInformacionAcademica(int id, InformacionAcademica infoAcademica) throws SQLException {
        infoAcademica.setFechaActualizacion(LocalDateTime.now());
        informacionAcademicaDao.updateInformacionAcademica(id, infoAcademica);
    }

    // Eliminar una información académica por ID
    public void deleteInformacionAcademica(int id) throws SQLException {
        informacionAcademicaDao.deleteInformacionAcademica(id);
    }
}

