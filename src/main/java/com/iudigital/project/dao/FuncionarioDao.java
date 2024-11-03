package com.iudigital.project.dao;

import com.iudigital.project.domain.Funcionario;
import com.iudigital.project.config.ConnectionConfig;
import com.iudigital.project.domain.EstadoCivil;
import com.iudigital.project.domain.GrupoFamiliar;
import com.iudigital.project.domain.InformacionAcademica;
import com.iudigital.project.domain.Sexo;
import com.iudigital.project.domain.TipoIdentificacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


  public class FuncionarioDao {

    

    private static final String GET_FUNCIONARIOS = "SELECT f.*, ti.tipo AS tipo_identificacion, \n" +
"s.sexo AS genero, ec.estado_civil AS estado_civil, \n" +
"gf.id_grupo_familiar, gf.nombre AS nombre_familiar, gf.apellido AS apellido_familiar, \n" +
"gf.parentesco AS parentesco_familiar, gf.fecha_nacimiento AS fecha_nacimiento_familiar, \n" +
"ia.titulo AS titulo_academico, ia.fecha_creacion AS fecha_creacion_academica \n" +
"FROM funcionarios f \n" +
"INNER JOIN tipos_identificacion ti ON f.id_tipo = ti.id_tipo \n" +
"INNER JOIN sexos s ON f.id_sexo = s.id_sexo \n" +
"INNER JOIN estados_civiles ec ON f.id_estado_civil = ec.id_estado_civil \n" +
"LEFT JOIN grupos_familiares gf ON f.id_funcionario = gf.id_funcionario \n" +
"LEFT JOIN informaciones_academica ia ON f.id_funcionario = ia.id_funcionario;";
    private static final String CREATE_FUNCIONARIO = "INSERT INTO funcionarios(id_tipo, id_sexo, id_estado_civil, "
            + "numero_identificacion, nombre, apellido, direccion, telefono, fecha_nacimiento, fecha_creacion) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_FUNCIONARIO_BY_ID = "SELECT f.*, ti.tipo, s.sexo, ec.estado_civil FROM funcionarios f "
            + "INNER JOIN tipos_identificacion ti ON f.id_tipo = ti.id_tipo "
            + "INNER JOIN sexos s ON f.id_sexo = s.id_sexo "
            + "INNER JOIN estados_civiles ec ON f.id_estado_civil = ec.id_estado_civil "
            + "WHERE f.id_funcionario = ?";
    private static final String UPDATE_FUNCIONARIO = "UPDATE funcionarios SET id_tipo = ?, id_sexo = ?, id_estado_civil = ?, "
            + "numero_identificacion = ?, nombre = ?, apellido = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, "
            + "fecha_actualizacion = ? WHERE id_funcionario = ?";
    private static final String DELETE_FUNCIONARIO = "DELETE FROM funcionarios WHERE id_funcionario = ?";

    public List<Funcionario> getFuncionarios() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_FUNCIONARIOS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(resultSet.getInt("id_funcionario"));
                funcionario.setTipoIdentificacion(new TipoIdentificacion(resultSet.getInt("id_tipo"), resultSet.getString("tipo_identificacion")));
                funcionario.setSexo(new Sexo(resultSet.getInt("id_sexo"), resultSet.getString("genero")));
                funcionario.setEstadoCivil(new EstadoCivil(resultSet.getInt("id_estado_civil"), resultSet.getString("estado_civil")));
                funcionario.setNumeroIdentificacion(resultSet.getString("numero_identificacion"));
                funcionario.setNombre(resultSet.getString("nombre"));
                funcionario.setApellido(resultSet.getString("apellido"));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getObject("fecha_nacimiento", LocalDate.class));
                funcionario.setFechaCreacion(resultSet.getObject("fecha_creacion", LocalDateTime.class));
                funcionario.setFechaActualizacion(resultSet.getObject("fecha_actualizacion", LocalDateTime.class));

                // Datos Relacionales
                String nombreFamiliar = resultSet.getString("nombre_familiar");
                if (nombreFamiliar != null) {
                    GrupoFamiliar grupoFamiliar = new GrupoFamiliar();
                    grupoFamiliar.setNombre(nombreFamiliar);
                    grupoFamiliar.setApellido(resultSet.getString("apellido_familiar"));
                    grupoFamiliar.setParentesco(resultSet.getString("parentesco_familiar"));
                    grupoFamiliar.setFechaNacimiento(resultSet.getObject("fecha_nacimiento_familiar", LocalDate.class));
                    funcionario.getGruposFamiliares().add(grupoFamiliar);
                }

                // Cargar información académica
                String tituloAcademico = resultSet.getString("titulo_academico");
                if (tituloAcademico != null) {
                    InformacionAcademica infoAcademica = new InformacionAcademica();
                    infoAcademica.setTitulo(tituloAcademico);
                    infoAcademica.setFechaCreacion(resultSet.getObject("fecha_creacion_academica", LocalDateTime.class));
                    funcionario.getInformacionAcademica().add(infoAcademica);
                }

                funcionarios.add(funcionario);
            }
        }
        return funcionarios;
    }

    


       
    

    public void createFuncionario(Funcionario funcionario) throws SQLException {
    try (Connection connection = ConnectionConfig.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(CREATE_FUNCIONARIO)) {

        preparedStatement.setInt(1, funcionario.getTipoIdentificacion().getId());
        preparedStatement.setInt(2, funcionario.getSexo().getId());
        preparedStatement.setInt(3, funcionario.getEstadoCivil().getId());
        preparedStatement.setString(4, funcionario.getNumeroIdentificacion());
        preparedStatement.setString(5, funcionario.getNombre());
        preparedStatement.setString(6, funcionario.getApellido());
        preparedStatement.setString(7, funcionario.getDireccion());
        preparedStatement.setString(8, funcionario.getTelefono());
        preparedStatement.setDate(9, java.sql.Date.valueOf(funcionario.getFechaNacimiento()));
        preparedStatement.setObject(10, LocalDateTime.now());
        preparedStatement.executeUpdate();
    }
}
    
    public Funcionario getFuncionarioById(int id) throws SQLException {
    Funcionario funcionario = null;

    try (Connection connection = ConnectionConfig.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(GET_FUNCIONARIO_BY_ID)) {

        preparedStatement.setInt(1, id);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                funcionario = new Funcionario();
                funcionario.setIdFuncionario(resultSet.getInt("id_funcionario"));
                funcionario.setTipoIdentificacion(new TipoIdentificacion(resultSet.getInt("id_tipo"), resultSet.getString("tipo")));
                funcionario.setSexo(new Sexo(resultSet.getInt("id_sexo"), resultSet.getString("sexo")));
                funcionario.setEstadoCivil(new EstadoCivil(resultSet.getInt("id_estado_civil"), resultSet.getString("estado_civil")));
                funcionario.setNumeroIdentificacion(resultSet.getString("numero_identificacion"));
                funcionario.setNombre(resultSet.getString("nombre"));
                funcionario.setApellido(resultSet.getString("apellido"));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getObject("fecha_nacimiento", LocalDate.class));
                funcionario.setFechaCreacion(resultSet.getObject("fecha_creacion", LocalDateTime.class));
                funcionario.setFechaActualizacion(resultSet.getObject("fecha_actualizacion", LocalDateTime.class));
            }
        }
    }
    return funcionario;
}



       

    public void updateFuncionario(int id, Funcionario funcionario) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_FUNCIONARIO);
            preparedStatement.setInt(1, funcionario.getTipoIdentificacion().getId());
            preparedStatement.setInt(2, funcionario.getSexo().getId());
            preparedStatement.setInt(3, funcionario.getEstadoCivil().getId());
            preparedStatement.setString(4, funcionario.getNumeroIdentificacion());
            preparedStatement.setString(5, funcionario.getNombre());
            preparedStatement.setString(6, funcionario.getApellido());
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
            preparedStatement.setDate(9, java.sql.Date.valueOf(funcionario.getFechaNacimiento()));
            preparedStatement.setObject(10, LocalDateTime.now());
            preparedStatement.setInt(11, id);
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

    public void deleteFuncionario(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FUNCIONARIO);
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
}
