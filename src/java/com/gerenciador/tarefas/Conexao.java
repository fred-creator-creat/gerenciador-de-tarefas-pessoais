package com.gerenciador.tarefas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/gerenciador_tarefas";
    private static final String USER = "root";  // Altere se necessário
    private static final String PASSWORD = "Fcd127442$";  // Altere se necessário

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}