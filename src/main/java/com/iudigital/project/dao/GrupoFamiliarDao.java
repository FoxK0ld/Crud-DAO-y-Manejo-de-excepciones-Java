package com.iudigital.project.dao;
import com.iudigital.project.domain.GrupoFamiliar;
import com.iudigital.project.config.ConnectionConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class GrupoFamiliarDao {
    
    private static final String GET_GRUPOSFAMILIARES = "SELECT * FROM grupos_familiares";
    private static final String CREATE_GRUPOSFAMILIARES = "INSERT INTO grupos_familiares(id_funcionario, nombre,"
            + " apellido, parentesco, fecha_nacimiento, fecha_creacion) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String GET_GRUPOFAMILIAR_BY_ID = "SELECT * FROM grupos_familiares WHERE id_grupo_familiar = ?";
    private static final String UPDATE_GRUPOFAMILIAR = "UPDATE grupos_familiares SET id_funcionario = ?, nombre = ?,"
            + " apellido = ?, parentesco = ?, fecha_nacimiento = ?, fecha_actualizacion = ? WHERE id_grupo_familiar = ?";
    private static final String DELETE_GRUPOFAMILIAR = "DELETE FROM grupos_familiares WHERE id_grupo_familiar = ?";

    public List<GrupoFamiliar> getGruposFamiliares() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<GrupoFamiliar> gruposFamiliares = new ArrayList<>();
        
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_GRUPOSFAMILIARES);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                GrupoFamiliar grupoFamiliar = new GrupoFamiliar();
                grupoFamiliar.setIdGrupoFamiliar(resultSet.getInt("id_grupo_familiar"));
                grupoFamiliar.setIdFuncionario(resultSet.getInt("id_funcionario"));
                grupoFamiliar.setNombre(resultSet.getString("nombre"));
                grupoFamiliar.setApellido(resultSet.getString("apellido"));
                grupoFamiliar.setParentesco(resultSet.getString("parentesco"));
                grupoFamiliar.setFechaNacimiento(resultSet.getObject("fecha_nacimiento", LocalDate.class));
                grupoFamiliar.setFechaCreacion(resultSet.getObject("fecha_creacion", LocalDateTime.class));
                grupoFamiliar.setFechaActualizacion(resultSet.getObject("fecha_actualizacion", LocalDateTime.class));
                
                gruposFamiliares.add(grupoFamiliar);
            }
            return gruposFamiliares;
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

    public void createGrupoFamiliar(GrupoFamiliar grupoFamiliar) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_GRUPOSFAMILIARES);
            preparedStatement.setInt(1, grupoFamiliar.getIdFuncionario());
            preparedStatement.setString(2, grupoFamiliar.getNombre());
            preparedStatement.setString(3, grupoFamiliar.getApellido());
            preparedStatement.setString(4, grupoFamiliar.getParentesco());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(grupoFamiliar.getFechaCreacion()));
            preparedStatement.setObject(6, LocalDateTime.now());
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

    public GrupoFamiliar getGrupoFamiliarById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        GrupoFamiliar grupoFamiliar = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_GRUPOFAMILIAR_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                grupoFamiliar = new GrupoFamiliar();
                grupoFamiliar.setIdGrupoFamiliar(resultSet.getInt("id_grupo_familiar"));
                grupoFamiliar.setIdFuncionario(resultSet.getInt("id_funcionario"));
                grupoFamiliar.setNombre(resultSet.getString("nombre"));
                grupoFamiliar.setApellido(resultSet.getString("apellido"));
                grupoFamiliar.setParentesco(resultSet.getString("parentesco"));
                grupoFamiliar.setFechaNacimiento(resultSet.getObject("fecha_nacimiento", LocalDate.class));
                grupoFamiliar.setFechaCreacion(resultSet.getObject("fecha_creacion", LocalDateTime.class));
                grupoFamiliar.setFechaActualizacion(resultSet.getObject("fecha_actualizacion", LocalDateTime.class));
            }
            return grupoFamiliar;
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

    public void updateGrupoFamiliar(int id, GrupoFamiliar grupoFamiliar) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_GRUPOFAMILIAR);
            preparedStatement.setInt(1, grupoFamiliar.getIdFuncionario());
            preparedStatement.setString(2, grupoFamiliar.getNombre());
            preparedStatement.setString(3, grupoFamiliar.getApellido());
            preparedStatement.setString(4, grupoFamiliar.getParentesco());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(grupoFamiliar.getFechaCreacion()));
            preparedStatement.setObject(6, LocalDateTime.now());
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

    public void deleteGrupoFamiliar(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_GRUPOFAMILIAR);
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

    public List<GrupoFamiliar> getGruposFamiliaresPorFuncionario(int funcionarioId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
