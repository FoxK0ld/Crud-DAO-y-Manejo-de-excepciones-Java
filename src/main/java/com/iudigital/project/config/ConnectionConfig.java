package com.iudigital.project.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {

    private static final String URL = "jdbc:postgresql://localhost:5432/funcionarios-iud";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Maquinadelmal2022$";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
