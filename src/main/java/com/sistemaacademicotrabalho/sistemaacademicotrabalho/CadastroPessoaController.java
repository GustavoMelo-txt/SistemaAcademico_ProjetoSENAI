package com.sistemaacademicotrabalho.sistemaacademicotrabalho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class CadastroPessoaController extends MostrarTelas {

    @FXML
    private RadioButton botaoProfessor;
    @FXML
    private RadioButton botaoAluno;
    @FXML
    private ToggleGroup tipoPessoa;
    private static String botaoSelecionado;

    @FXML
    private Pane paneEspecialidades;

    @FXML
    private TextField nomeCompleto;
    @FXML
    private TextField cpf;
    @FXML
    private MenuButton genero;
    @FXML
    private DatePicker dataNasc;
    @FXML
    private TextField email;
    @FXML
    private TextField telefone1;
    @FXML
    private TextField telefone2;
    @FXML
    private TextArea especialidades;

    @Override
    @FXML
    public void initialize() {
        super.initialize();
        dataNasc.setDayCellFactory(getDateCellFactory(LocalDate.now().minusYears(14)));

        botaoAluno.setUserData("aluno");
        botaoProfessor.setUserData("professor");
        botaoAluno.setSelected(true);
        botaoSelecionado = botaoAluno.getUserData().toString();
        tipoPessoa.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            if (t1 != null) {
                String selected = CadastroPessoaController.botaoSelecionado = t1.getUserData().toString();
                if ("aluno".equals(selected)) {
                    paneEspecialidades.setVisible(false);
                    paneEspecialidades.setManaged(false);
                } else {
                    paneEspecialidades.setVisible(true);
                    paneEspecialidades.setManaged(true);
                }
            }
        });

        paneEspecialidades.setVisible(false);
        paneEspecialidades.setManaged(false);
    }

    @FXML
    public void atualizarGenero(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        String generoSelecionado = menuItem.getText();
        genero.setTextFill(Paint.valueOf("#000000"));
        genero.setText(generoSelecionado);
    }

    @FXML
    public void concluir() {


            System.out.println("Método concluir foi chamado.");
            Pessoa pessoa = new Pessoa(nomeCompleto.getText(),
                    String.valueOf(dataNasc.getValue()),
                    cpf.getText(),
                    (genero.getText().equals("Masculino") ? "M" : (genero.getText().equals("Feminino") ? "F" : "N")),
                    email.getText(),
                    telefone1.getText(),
                    telefone2.getText(),
                    especialidades.getText());

            if (pessoa.nome.isEmpty() || pessoa.cpf.isEmpty() || pessoa.genero.isEmpty() || pessoa.email.isEmpty() || pessoa.telefone1.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Campos obrigatórios");
                alert.setHeaderText(null);
                alert.setContentText("Preencha todos os campos!");
                alert.showAndWait();
            } else {


                try (Connection conn = DBConnection.getConnection()) {
                    if (botaoSelecionado.equals("aluno")) {
                        limparCampos();

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Sucesso!");
                        alert.setHeaderText(null);
                        alert.setContentText("Cadastro concluído com sucesso.");
                        alert.showAndWait();

                        PreparedStatement stmt = conn.prepareStatement("insert into alunos (nome, dataNasc, cpf,email, telefone1, telefone2, genero) values (?, ?, ?, ?, ?, ?, ?);");
                        stmt.setString(1, pessoa.nome);
                        stmt.setDate(2, Date.valueOf(pessoa.dataNasc));
                        stmt.setString(3, pessoa.cpf);
                        stmt.setString(4, pessoa.email);
                        stmt.setString(5, pessoa.telefone1);
                        stmt.setString(6, pessoa.telefone2);
                        stmt.setString(7, pessoa.genero);

                        stmt.executeUpdate();
                    } else {
                        if (!pessoa.especialidades.isEmpty()) {
                            limparCampos();

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Sucesso!");
                            alert.setHeaderText(null);
                            alert.setContentText("Cadastro concluído com sucesso.");
                            alert.showAndWait();

                            PreparedStatement stmt = conn.prepareStatement("insert into professores (nome, especialidade, dataNasc, cpf,email, telefone1, telefone2, genero) values (?, ?, ?, ?, ?, ?, ?, ?);");
                            stmt.setString(1, pessoa.nome);
                            stmt.setString(2, pessoa.especialidades);
                            stmt.setDate(3, Date.valueOf(pessoa.dataNasc));
                            stmt.setString(4, pessoa.cpf);
                            stmt.setString(5, pessoa.email);
                            stmt.setString(6, pessoa.telefone1);
                            stmt.setString(7, pessoa.telefone2);
                            stmt.setString(8, pessoa.genero);

                            stmt.executeUpdate();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Campos obrigatórios");
                            alert.setHeaderText(null);
                            alert.setContentText("Preencha todos os campos!");
                            alert.showAndWait();
                        }
                    }


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
    }

    private void limparCampos() {
        nomeCompleto.clear();
        cpf.clear();
        genero.setText("Selecione");
        genero.setTextFill(Paint.valueOf("#dddddd"));
        dataNasc.setValue(null);
        email.clear();
        telefone1.clear();
        telefone2.clear();
        especialidades.clear();
    }

    private Callback<DatePicker, DateCell> getDateCellFactory(LocalDate maxDate) {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Disable all dates after the max allowed date (today minus 18 years)
                if (date.isAfter(maxDate) || date.isAfter(LocalDate.now())) {
                    setDisable(true);
                }
            }
        };
    }

    class Pessoa {
        private String nome;
        private String dataNasc;
        private String cpf;
        private String genero;
        private String email;
        private String telefone1;
        private String telefone2;
        private String especialidades;

        public Pessoa(String nome, String dataNasc, String cpf, String genero, String email, String telefone1, String telefone2) {
            this.nome = nome;
            this.dataNasc = dataNasc;
            this.cpf = cpf;
            this.genero = genero;
            this.email = email;
            this.telefone1 = telefone1;
            this.telefone2 = telefone2;
        }

        public Pessoa(String nome, String dataNasc, String cpf, String genero, String email, String telefone1, String telefone2, String especialidades) {
            this.nome = nome;
            this.dataNasc = dataNasc;
            this.cpf = cpf;
            this.genero = genero;
            this.email = email;
            this.telefone1 = telefone1;
            this.telefone2 = telefone2;
            this.especialidades = especialidades;
        }
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
    private void botaoInicio(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirTelaInicialInstituicao(stageAtual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void botaoTurmas(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirCadastroTurma(stageAtual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}