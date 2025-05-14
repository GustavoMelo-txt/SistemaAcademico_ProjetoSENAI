package com.sistemaacademicotrabalho.sistemaacademicotrabalho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class TurmaController extends MostrarTelas {

    @FXML
    private void botaoRelatorio(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirRelatorio(stageAtual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void botaoInicio(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirTelaInicialInstituicao(stageAtual);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}