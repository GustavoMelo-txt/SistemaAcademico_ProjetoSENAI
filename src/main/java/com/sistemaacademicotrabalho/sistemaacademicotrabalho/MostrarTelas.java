package com.sistemaacademicotrabalho.sistemaacademicotrabalho;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MostrarTelas {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    @FXML
    protected ImageView logoSenai;

    @FXML
    public void initialize() {
        if (logoSenai != null) {
            logoSenai.setOnMouseClicked(event -> {
                try {
                    Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    abrirTelaInicialInstituicao(stageAtual);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            logoSenai.setStyle("-fx-cursor: hand;");
        }
    }

    private void abrirTela(Stage stageAtual, String fxmlPath, String title) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setTitle(title);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        newStage.setScene(scene);

        newStage.setResizable(false);

        if (stageAtual != null) {
            stageAtual.close();
        }

        newStage.show();
    }

    public void abrirTelaLogin(Stage stageAtual) throws Exception {
        abrirTela(stageAtual, "telalogin-view.fxml", "Tela de Login");
    }

    public void abrirTelaInicialInstituicao(Stage stageAtual) throws Exception {
        abrirTela(stageAtual, "telainicialinstituicao-view.fxml", "Tela Inicial da Instituição");
    }

    public void abrirTelaTurma(Stage stageAtual) throws Exception {
        abrirTela(stageAtual, "turma-view.fxml", "Turma");
    }

    public void abrirRelatorio(Stage stageAtual) throws Exception {
        abrirTela(stageAtual, "teladashboard-view.fxml", "Relatórios");
    }

    public void abrirCadastroTurma(Stage stageAtual) throws Exception {
        abrirTela(stageAtual, "cadastroturmas-view.fxml", "Cadastro de Turmas");
    }

    public void abrirCadastroPessoa(Stage stageAtual) throws Exception {
        abrirTela(stageAtual, "cadastropessoa-view.fxml", "Cadastro de Pessoas");
    }
}