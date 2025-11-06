package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/app_java?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";    // Usuário padrão do XAMPP
    private static final String PASSWORD = "";    // Senha padrão é vazia
    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }
}
