package com.sistemaacademicotrabalho.sistemaacademicotrabalho;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class CadastroTurmasController extends MostrarTelas {

    @FXML private TextField txtNomeTurma;
    @FXML private TextField txtCurso;
    @FXML private MenuButton menuTurno;
    @FXML private DatePicker dateInicio;
    @FXML private DatePicker dateFim;

    private String turnoSelecionado;

    @Override
    @FXML
    // Confirma obrigatóriamente que o Turno vai ser pego pelo programa e estiliza um pouco pra ficar visualmente melhor pro usuario
    // Tive problemas com ao fazer o código, por isso vi a necessidade de fazer isso em um initialize
    public void initialize() {
        super.initialize();
        for (MenuItem item : menuTurno.getItems()) {
            item.setOnAction(e -> {
                turnoSelecionado = item.getText();
                menuTurno.setText(item.getText());
                menuTurno.setStyle("-fx-text-fill: #000000; -fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 3;");
            });
        }
    }

    @FXML
    // Dessa vez tentei colocar o programa pra aparecer mensagens no terminal enquanto o programa roda, por isso dous println no meio de um metodo
    // Metodo feito pra salvar as informaçoes informadas pelo usuario na tabela de turmas, além de verificar se o curso existe na tabela de cursos
    private void salvarTurma() {
        try {
            System.out.println("Tentativa de salvamento iniciada");

            // Achei melhor fazer um metodo booleano dessa vez pra fazer todas as verificações necessárias nele
            // caso ele retorne false ele vai mostrar um Alert de erro e nao ira enviar as informações pro banco de dados
            if (!validarCampos()) return;

            System.out.println("Campos validados:");
            System.out.println("Nome Turma: " + txtNomeTurma.getText());
            System.out.println("Curso: " + txtCurso.getText());
            System.out.println("Turno: " + turnoSelecionado);
            System.out.println("Data Início: " + dateInicio.getValue());
            System.out.println("Data Fim: " + dateFim.getValue());

            Integer idCurso = InsertTableTurma.obterIdCurso(txtCurso.getText());
            System.out.println("ID Curso obtido: " + idCurso);

            if (idCurso == null) {
                // Mostrar alerta é um metodo feito pra mostrar os Alert de forma mais rapida, já que imaginei que usariamos bastante
                mostrarAlerta("Erro", "Curso não encontrado: " + txtCurso.getText(), Alert.AlertType.ERROR);
                return;
            }

            // pra um metodo na classe InsertTableTurma pra fazer o insert das informações na tabela de turmas
            InsertTableTurma.inserirTurma(
                    txtNomeTurma.getText(),
                    turnoSelecionado,
                    dateInicio.getValue(),
                    dateFim.getValue(),
                    idCurso
            );

            // Ao concluir, aparece um alert de conclusão, quando o usuario confirmar o alert vai se fechar e a tela inicial ira aparecer
            mostrarAlerta("Sucesso", "Turma cadastrada com sucesso!", Alert.AlertType.INFORMATION);
            limparCampos();

        } catch (Exception e) {
            System.err.println("Erro crítico:");
            e.printStackTrace();
            mostrarAlerta("Falha Grave", "Erro: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Validação dos campos de forma mais eficiente
    private boolean validarCampos() {
        if (txtNomeTurma.getText().isEmpty() ||
                txtCurso.getText().isEmpty() ||
                turnoSelecionado == null ||
                dateInicio.getValue() == null ||
                dateFim.getValue() == null) {

            mostrarAlerta("Campos obrigatórios", "Preencha todos os campos!", Alert.AlertType.WARNING);
            return false;
        }

        if (dateInicio.getValue().isAfter(dateFim.getValue())) {
            mostrarAlerta("Datas inválidas", "Data de início não pode ser após a data de término!", Alert.AlertType.WARNING);
            return false;
        }

        return true;
    }

    private void limparCampos() {
        txtNomeTurma.clear();
        txtCurso.clear();
        menuTurno.setText("Turno");
        dateInicio.setValue(null);
        dateFim.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // A partir daqui, todos os metodos são especificamente pra trocar de tela
    // O unico diferente entre eles é o botaoClicado(), ja que tive que fazer um sem parametros puxar o outro com parametros
    // Pra poder usar o botaoClicado() pra mudar de tela la no salvarTurma()
    @FXML
    private void botaoClicado() throws Exception {
        Stage stageAtual = (Stage) txtNomeTurma.getScene().getWindow();
        abrirTelaInicialInstituicao(stageAtual);
    }

    @FXML
    private void botaoClicado(ActionEvent event) throws Exception {
        botaoClicado();
    }

    @FXML
    private void botaoRelatorio(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirRelatorio(stageAtual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
    private void botaoAlunoProfessor(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirCadastroPessoa(stageAtual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}