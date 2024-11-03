package com.iudigital.project.dao;
import com.iudigital.project.domain.InformacionAcademica;
import com.iudigital.project.config.ConnectionConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InformacionAcademicaDao {
    
    private static final String GET_INFORMACIONESACADEMICA = "SELECT * FROM informaciones_academica";
    private static final String CREATE_INFORMACIONESACADEMICA = "INSERT INTO informaciones_academica(id_funcionario, id_universidad,"
            + " id_nivel_estudio, titulo, fecha_creacion) VALUES(?, ?, ?, ?, ?)";
    private static final String GET_INFORMACIONACADEMICA_BY_ID = "SELECT * FROM informaciones_academica WHERE id_informacion_academica = ?";
    private static final String UPDATE_INFORMACIONACADEMICA = "UPDATE informaciones_academica SET id_funcionario = ?, id_universidad = ?,"
            + " id_nivel_estudio = ?, titulo = ?, fecha_actualizacion = ? WHERE id_informacion_academica = ?";
    private static final String DELETE_INFORMACIONACADEMICA = "DELETE FROM informaciones_academica WHERE id_informacion_academica = ?";

    public List<InformacionAcademica> getInformacionesAcademica() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<InformacionAcademica> infosAcademica = new ArrayList<>();
        
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_INFORMACIONESACADEMICA);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                InformacionAcademica infoAcademica = new InformacionAcademica();
                infoAcademica.setIdInformacionAcademica(resultSet.getInt("id_informacion_academica"));
                infoAcademica.setIdFuncionario(resultSet.getInt("id_funcionario"));
                infoAcademica.setIdUniversidad(resultSet.getInt("id_universidad"));
                infoAcademica.setIdNivelEstudio(resultSet.getInt("id_nivel_estudio"));
                infoAcademica.setTitulo(resultSet.getString("titulo"));
                infoAcademica.setFechaCreacion(resultSet.getObject("fecha_creacion", LocalDateTime.class));
                infoAcademica.setFechaActualizacion(resultSet.getObject("fecha_actualizacion", LocalDateTime.class));
                
                infosAcademica.add(infoAcademica);
            }
            return infosAcademica;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void createInformacionAcademica(InformacionAcademica infoAcademica) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_INFORMACIONESACADEMICA);
            preparedStatement.setInt(1, infoAcademica.getIdFuncionario());
            preparedStatement.setInt(2, infoAcademica.getIdUniversidad());
            preparedStatement.setInt(3, infoAcademica.getIdNivelEstudio());
            preparedStatement.setString(4, infoAcademica.getTitulo());
            preparedStatement.setObject(5, LocalDateTime.now());
            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public InformacionAcademica getInformacionAcademicaById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        InformacionAcademica infoAcademica = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_INFORMACIONACADEMICA_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                infoAcademica = new InformacionAcademica();
                infoAcademica.setIdInformacionAcademica(resultSet.getInt("id_informacion_academica"));
                infoAcademica.setIdFuncionario(resultSet.getInt("id_funcionario"));
                infoAcademica.setIdUniversidad(resultSet.getInt("id_universidad"));
                infoAcademica.setIdNivelEstudio(resultSet.getInt("id_nivel_estudio"));
                infoAcademica.setTitulo(resultSet.getString("titulo"));
                infoAcademica.setFechaCreacion(resultSet.getObject("fecha_creacion", LocalDateTime.class));
                infoAcademica.setFechaActualizacion(resultSet.getObject("fecha_actualizacion", LocalDateTime.class));
            }
            return infoAcademica;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateInformacionAcademica(int id, InformacionAcademica infoAcademica) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_INFORMACIONACADEMICA);
            preparedStatement.setInt(1, infoAcademica.getIdFuncionario());
            preparedStatement.setInt(2, infoAcademica.getIdUniversidad());
            preparedStatement.setInt(3, infoAcademica.getIdNivelEstudio());
            preparedStatement.setString(4, infoAcademica.getTitulo());
            preparedStatement.setObject(5, LocalDateTime.now());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteInformacionAcademica(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_INFORMACIONACADEMICA);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<InformacionAcademica> getInformacionAcademicaPorFuncionario(int funcionarioId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
