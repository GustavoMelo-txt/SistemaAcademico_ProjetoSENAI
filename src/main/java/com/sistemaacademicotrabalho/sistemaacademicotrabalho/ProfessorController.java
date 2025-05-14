package com.sistemaacademicotrabalho.sistemaacademicotrabalho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ProfessorController extends MostrarTelas {

    @FXML
    private void botaoTurmas(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirTelaTurma(stageAtual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void botaoRelatorio (ActionEvent event){
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirRelatorio(stageAtual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void botaoInicio (ActionEvent event){
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirTelaInicialInstituicao(stageAtual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

