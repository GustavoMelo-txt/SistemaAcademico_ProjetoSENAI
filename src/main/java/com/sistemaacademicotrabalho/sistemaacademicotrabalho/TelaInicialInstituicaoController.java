package com.sistemaacademicotrabalho.sistemaacademicotrabalho;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class TelaInicialInstituicaoController extends MostrarTelas {

    @FXML
    protected void botaoCadastrarPessoa(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirCadastroPessoa(stageAtual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void cadastrarTurma(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirCadastroTurma(stageAtual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void botaoRelatorio(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirRelatorio(stageAtual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void botaoInicio(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirTelaInicialInstituicao(stageAtual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void botaoTurmas(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirTelaTurma(stageAtual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}