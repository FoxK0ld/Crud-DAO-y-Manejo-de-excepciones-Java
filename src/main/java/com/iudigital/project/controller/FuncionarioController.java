package com.iudigital.project.controller;

import com.iudigital.project.dao.FuncionarioDao;
import com.iudigital.project.dao.GrupoFamiliarDao;
import com.iudigital.project.dao.InformacionAcademicaDao;
import com.iudigital.project.domain.Funcionario;
import com.iudigital.project.domain.GrupoFamiliar;
import com.iudigital.project.domain.InformacionAcademica;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class FuncionarioController {

    private FuncionarioDao funcionarioDao;
    private GrupoFamiliarDao grupoFamiliarDao;
    private InformacionAcademicaDao informacionAcademicaDao;

    public FuncionarioController() {
        funcionarioDao = new FuncionarioDao();
        grupoFamiliarDao = new GrupoFamiliarDao();
        informacionAcademicaDao = new InformacionAcademicaDao();
    }

    public List<Funcionario> getFuncionarios() throws SQLException {
        return funcionarioDao.getFuncionarios();
    }

    public void create(Funcionario funcionario) throws SQLException {
        funcionario.setFechaCreacion(LocalDateTime.now());
        funcionarioDao.createFuncionario(funcionario);
    }

    public Funcionario getFuncionario(int id) throws SQLException {
        return funcionarioDao.getFuncionarioById(id);
    }

    public void updateFuncionario(int id, Funcionario funcionario) throws SQLException {
        funcionario.setFechaActualizacion(LocalDateTime.now());
        funcionarioDao.updateFuncionario(id, funcionario);
    }

    public void deleteFuncionario(int id) throws SQLException {
        funcionarioDao.deleteFuncionario(id);
    }

    
    public List<GrupoFamiliar> getGruposFamiliaresPorFuncionario(int funcionarioId) throws SQLException {
        return grupoFamiliarDao.getGruposFamiliaresPorFuncionario(funcionarioId);
    }

    public void createGrupoFamiliar(GrupoFamiliar grupoFamiliar) throws SQLException {
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

    
    public List<InformacionAcademica> getInformacionAcademicaPorFuncionario(int funcionarioId) throws SQLException {
        return informacionAcademicaDao.getInformacionAcademicaPorFuncionario(funcionarioId);
    }

    public void createInformacionAcademica(InformacionAcademica informacionAcademica) throws SQLException {
        informacionAcademica.setFechaCreacion(LocalDateTime.now());
        informacionAcademicaDao.createInformacionAcademica(informacionAcademica);
    }

    public InformacionAcademica getInformacionAcademica(int id) throws SQLException {
        return informacionAcademicaDao.getInformacionAcademicaById(id);
    }

    public void updateInformacionAcademica(int id, InformacionAcademica informacionAcademica) throws SQLException {
        informacionAcademica.setFechaActualizacion(LocalDateTime.now());
        informacionAcademicaDao.updateInformacionAcademica(id, informacionAcademica);
    }

    public void deleteInformacionAcademica(int id) throws SQLException {
        informacionAcademicaDao.deleteInformacionAcademica(id);
    }
}
