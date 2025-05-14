package com.sistemaacademicotrabalho.sistemaacademicotrabalho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class InsertTableTurma {

    public static void inserirTurma(String nomeTurma, String turno, LocalDate dataInicio, LocalDate dataFim, int idCurso) {
        String sql = """
        INSERT INTO turmas 
        (nome, turno, dataInicio, dataFim, idCurso) 
        VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nomeTurma);
                stmt.setString(2, turno);
                stmt.setDate(3, java.sql.Date.valueOf(dataInicio));
                stmt.setDate(4, java.sql.Date.valueOf(dataFim));
                stmt.setInt(5, idCurso);

                int rowsAffected = stmt.executeUpdate();
                System.out.println("Linhas afetadas: " + rowsAffected);
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException("Erro na transação: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro de conexão: " + e.getMessage());
        }
    }

    public static Integer obterIdCurso(String nomeCurso) {
        String sql = "SELECT idCurso FROM cursos WHERE nome = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeCurso);
            System.out.println("Executando query: " + stmt);

            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("idCurso");
                    System.out.println("Curso encontrado: ID " + id);
                    return id;
                }
                System.out.println("Nenhum curso encontrado para: " + nomeCurso);
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar curso:");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}