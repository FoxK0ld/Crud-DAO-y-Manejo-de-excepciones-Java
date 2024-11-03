package com.iudigital.project.dao;

import com.iudigital.project.domain.TipoIdentificacion;
import com.iudigital.project.config.ConnectionConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TipoIdentificacionDao {

    private static final String GET_TIPOSIDENTIFICACION = "select * from tipos_identificacion";
    
    
    public List<TipoIdentificacion> getTiposIdentificacion() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TipoIdentificacion> tiposIdentificaciones  = new ArrayList<>();
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_TIPOSIDENTIFICACION);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();
                tipoIdentificacion.setId(resultSet.getInt("id_tipo"));
                tipoIdentificacion.setTipo(resultSet.getString("tipo"));
                tiposIdentificaciones.add(tipoIdentificacion);
            }
            return tiposIdentificaciones;
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
