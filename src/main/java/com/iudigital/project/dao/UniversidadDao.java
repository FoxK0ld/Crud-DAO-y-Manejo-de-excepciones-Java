package com.iudigital.project.dao;
import com.iudigital.project.domain.Universidad;
import com.iudigital.project.config.ConnectionConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UniversidadDao {
    
    private static final String GET_UNIVERSIDADES = "select * from universidades";
    
    
    public List<Universidad> getUniversidades() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Universidad> universidades  = new ArrayList<>();
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_UNIVERSIDADES);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Universidad universidad = new Universidad();
                universidad.setId(resultSet.getInt("id_universidad"));
                universidad.setTipo(resultSet.getString("tipo"));
                universidades.add(universidad);
            }
            return universidades;
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
