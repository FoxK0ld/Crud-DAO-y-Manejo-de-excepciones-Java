package com.iudigital.project.dao;
import com.iudigital.project.domain.NivelEstudio;
import com.iudigital.project.config.ConnectionConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NivelEstudioDao {
    
    private static final String GET_NIVELESESTUDIO = "select * from niveles_estudio";
    
    
    public List<NivelEstudio> getNivelesEstidio() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<NivelEstudio> nivelesEstudio  = new ArrayList<>();
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(GET_NIVELESESTUDIO );
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
               NivelEstudio nivelEstudio = new NivelEstudio();
                nivelEstudio.setId(resultSet.getInt("id_nivel_estudio"));
                nivelEstudio.setNivelEstudio(resultSet.getString("nivel_estudio"));
                nivelesEstudio.add(nivelEstudio);
            }
            return nivelesEstudio;
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
