package com.iudigital.project.dao;
import com.iudigital.project.domain.EstadoCivil;
import com.iudigital.project.config.ConnectionConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EstadoCivilDao {

    private static final String GET_ESTADOSCIVILES = "select * from estados_civiles";
    
    
    public List<EstadoCivil> getEstadosCiviles() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<EstadoCivil> estadosCiviles  = new ArrayList<>();
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_ESTADOSCIVILES);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EstadoCivil estadoCivil = new EstadoCivil();
                estadoCivil.setId(resultSet.getInt("id_estado_civil"));
                estadoCivil.setEstadoCivil(resultSet.getString("estado_civil"));
                estadosCiviles.add(estadoCivil);
            }
            return estadosCiviles;
        } finally {

            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }
        }

    }
}

