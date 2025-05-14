package com.sistemaacademicotrabalho.sistemaacademicotrabalho;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.stage.Stage;


public class DashboardController extends MostrarTelas {

    @FXML
    private BarChart<String, Number> barChartAprovacao;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private BarChart<String, Number> barChartNotas;

    @FXML
    private PieChart pieChartMedia;

    @Override
    @FXML
    public void initialize() {
        super.initialize();
        chartAprovacao();
        chartNotas();
        chartMedia(4.0);
    }

    @FXML
    private void chartAprovacao(){
        xAxis.setLabel(" ");
        yAxis.setLabel("Quantidade");

        int aprovados = 20;
        int reprovados = 17;

        XYChart.Series<String, Number> serieAprovados = new XYChart.Series<>();
        serieAprovados.setName("Aprovados");
        serieAprovados.getData().add(new XYChart.Data<>(" ", aprovados));

        XYChart.Series<String, Number> serieReprovados = new XYChart.Series<>();
        serieReprovados.setName("Reprovados");
        serieReprovados.getData().add(new XYChart.Data<>(" ", reprovados));

        barChartAprovacao.getData().addAll(serieAprovados, serieReprovados);
        barChartAprovacao.setLegendVisible(true);
    }

    @FXML
    private void chartNotas(){
        XYChart.Series<String, Number> seriesNotas1 = new XYChart.Series<>();
        XYChart.Series<String, Number> seriesNotas2 = new XYChart.Series<>();
        XYChart.Series<String, Number> seriesNotas3 = new XYChart.Series<>();
        XYChart.Series<String, Number> seriesNotas4 = new XYChart.Series<>();
        XYChart.Series<String, Number> seriesNotas5 = new XYChart.Series<>();
        XYChart.Series<String, Number> seriesNotas6 = new XYChart.Series<>();

        seriesNotas1.setName("");

        seriesNotas1.getData().add(new XYChart.Data<>(" ", 2));
        seriesNotas2.getData().add(new XYChart.Data<>(" ", 3));
        seriesNotas3.getData().add(new XYChart.Data<>(" ", 4));
        seriesNotas4.getData().add(new XYChart.Data<>(" ", 5));
        seriesNotas5.getData().add(new XYChart.Data<>(" ", 4));
        seriesNotas6.getData().add(new XYChart.Data<>(" ", 6));

        barChartNotas.getData().addAll(seriesNotas1, seriesNotas2, seriesNotas3, seriesNotas4, seriesNotas5, seriesNotas6);

    }

    @FXML
    private void chartMedia(Double media){
        double restante = 10 - media; // Supondo que a média máxima seja 10

        ObservableList<PieChart.Data> dadosPizza = FXCollections.observableArrayList(
                new PieChart.Data("Média", media),
                new PieChart.Data("Restante", restante)
        );

        pieChartMedia.setData(dadosPizza);
        pieChartMedia.setLabelsVisible(false);
    }

    @FXML
    private void botaoTurmas(ActionEvent event) {
        try {
            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            abrirTelaTurma(stageAtual);
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
