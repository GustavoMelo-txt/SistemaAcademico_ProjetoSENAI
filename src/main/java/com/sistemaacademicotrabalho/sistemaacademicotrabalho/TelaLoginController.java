package com.sistemaacademicotrabalho.sistemaacademicotrabalho;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaLoginController extends MostrarTelas {

    @FXML
    private TextField email;
    @FXML
    private PasswordField senha;
    @FXML
    private Label mensagem;

    @FXML
    private void botaoClicado(ActionEvent event) {
        String email = this.email.getText(), senha = this.senha.getText();
        if (email.equals("admin@admin.com") && senha.equals("admin")) {
            try {
                Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

                abrirTelaInicialInstituicao(stageAtual);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            this.email.setText("");
            this.senha.setText("");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!");
            alert.setHeaderText(null);
            alert.setContentText("Informações inválidas, tente novamente.");
            alert.showAndWait();
        }
    }
}