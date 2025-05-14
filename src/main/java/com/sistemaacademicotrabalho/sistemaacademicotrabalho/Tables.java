package com.sistemaacademicotrabalho.sistemaacademicotrabalho;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {
    public static void main(String[] args) throws SQLException {

        String[] queries = {"""
                create database if not exists EscolaTecnica;
                """,
                """
                use EscolaTecnica;
                """,
                """
                create table if not exists professores (
                idProfessor int auto_increment primary key,
                nome varchar(60),
                especialidade varchar (100),
                dataNasc date,
                cpf char(14),
                email varchar(255),
                telefone1 char(15),
                telefone2 char(15),
                genero char(1)
                );
                """,
                """
                create table if not exists alunos (
                    	idAluno int auto_increment primary key,
                      nome varchar(60),
                      dataNasc date,
                      cpf char(14),
                      email varchar(255),
                      telefone1 char(15),
                      telefone2 char(15),
                      genero char(1)
                    );
                """,
                """
                    create table if not exists cursos (
                    	idCurso int auto_increment primary key,
                      nome varchar(100),
                      cargaHoraria int
                    );
                """,
                """
                    create table if not exists turmas (
                    	idTurma int auto_increment primary key,
                      nome varchar(60),
                      turno varchar(10),
                      dataInicio date,
                      dataFim date,
                      idCurso int,
                      foreign key (idCurso) references cursos(idCurso)
                    );
                """,
                """
                    create table if not exists unidadesCurriculares (
                    	idUnidadeCurricular int auto_increment primary key,
                    	nome varchar(60),
                      idProfessor int,
                      idTurma int,
                      foreign key (idProfessor) references professores(idProfessor),
                      foreign key (idTurma) references turmas(idTurma)
                    );
                """,
                """
                    create table if not exists matriculas (
                    	idMatricula int auto_increment primary key,
                      dataMatricula date default current_timestamp,
                      idAluno int,
                      idTurma int,
                      foreign key (idAluno) references alunos(idAluno),
                      foreign key (idTurma) references turmas(idTurma)
                    );
                """,
                    """
                    create table if not exists desempenho (
                    	idDesempenho int auto_increment primary key,
                      pontuacao double,
                      faltas int,
                      frequencia decimal(3,2),
                      idAluno int,
                      idUnidadeCurricular int,
                      foreign key (idAluno) references alunos(idAluno),
                      foreign key (idUnidadeCurricular) references unidadesCurriculares(idUnidadeCurricular)
                    );
                    """};

        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            for (String query : queries) {
                stmt.execute(query);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
