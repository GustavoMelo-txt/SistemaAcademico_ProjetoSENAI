package com.sistemaacademicotrabalho.sistemaacademicotrabalho;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        // Configurações de conexão
        String url = "jdbc:mysql://localhost:3306/escolatecnica?"
                + "useSSL=false&"
                + "serverTimezone=UTC";

        // Removido o parâmetro query_cache_type que pode causar problemas
        String user = "root";
        String password = null; // Insira sua senha aqui

        try {
            // Registrar o driver (opcional para JDBC 4.0+, mas recomendado)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado", e);
        }

        // Tentar conectar e retornar a conexão
        return DriverManager.getConnection(url, user, null);
    }
}
