import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;


public class ListaBus extends Application {
    private Controller controle = new Controller();

    private ObservableList<busData> data = FXCollections.observableArrayList();

    private TableView<busData> table = new TableView<busData>();

    @Override
    public void start(Stage stage){

        stage.setTitle("Onibus -- Rio de Janeiro");
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        BorderPane cenario = new BorderPane();
        HBox informacoes = new HBox();
        VBox graficos = new VBox();
        informacoes.setStyle("-fx-border-color: #c9c9c9;" + "-fx-border-width: 1;" + "-fx-background-color: #f2f2f2;" +
                "-fx-border-style: solid hidden hidden hidden");
        ToolBar botoes = new ToolBar();

        // Mapa
        int width = (int)screenSize.getWidth()/4;
        int heigth = (int)screenSize.getHeight();
        String mapa = "https://maps.googleapis.com/maps/api/staticmap?size="+width+"x"+heigth+"&maptype=roadmap&markers=icon:https://goo.gl/xpmPM1%7C-23.000011,-43.365980";
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(mapa);

        webView.setMaxWidth(width);

        // Tabela
        TableColumn<busData,String> fstCol = new TableColumn<busData,String>("Data Hora");
        fstCol.setCellValueFactory(cellData -> cellData.getValue().dataHoraProperty());

        TableColumn<busData,String> sndCol = new TableColumn<busData,String>("Ordem");
        sndCol.setCellValueFactory(cellData -> cellData.getValue().ordemProperty());

        TableColumn<busData,String> trdCol = new TableColumn<busData,String>("Linha");
        trdCol.setCellValueFactory(cellData -> cellData.getValue().linhaProperty());

        TableColumn<busData,String> frtCol = new TableColumn<busData,String>("Latitude");
        frtCol.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty());

        TableColumn<busData,String> fivCol = new TableColumn<busData,String>("Longitude");
        fivCol.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty());

        TableColumn<busData,Double> sixCol = new TableColumn<busData,Double>("Velocidade");
        sixCol.setCellValueFactory(cellData -> cellData.getValue().velocidadeProperty().asObject());

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(fstCol, sndCol, trdCol, frtCol, fivCol, sixCol);

        table.setItems(data);

        table.setMinWidth(width*2);
        table.setMaxWidth(width*2);

        // Mudar o mapa quando clicar em algum item
        table.getSelectionModel().selectedItemProperty().addListener(y -> {
            if (table.getSelectionModel().getSelectedItem() != null) {
                webEngine.load("https://maps.googleapis.com/maps/api/staticmap?size="+width+"x"+heigth+
                        "&maptype=roadmap&markers=icon:https://goo.gl/xpmPM1%7C"+
                        table.getSelectionModel().getSelectedItem().getLatitude()+","+
                        table.getSelectionModel().getSelectedItem().getLongitude());
            }
        });


        // Graficos
        // Pie Chart
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Parados", 50),
                        new PieChart.Data("Em Movimento", 50));
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Onibus");

        // Bar Chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart =
                new BarChart<String, Number>(xAxis, yAxis);
        barChart.setTitle("Onibus em Movimento");
        xAxis.setLabel("Linha");
        yAxis.setLabel("Num Bus");
        barChart.setLegendVisible(false);

        graficos.getChildren().addAll(pieChart, barChart);
        graficos.setMinWidth(width);
        graficos.setMaxWidth(width);

        // Informações
        Label horaMaisRecente = new Label("Data-Hora Mais Recente : Sem Dados ");
        Label horaMenosRecente = new Label("Data-Hora Menos Recente : Sem Dados ");
        Label ultimaLeituraServidor = new Label("Ultima Leitura Servidor : Sem Dados ");
        Label numeroVeiculos = new Label("Numero de Veiculos : Sem dados");

        informacoes.getChildren().addAll(ultimaLeituraServidor, new Separator(Orientation.VERTICAL), horaMaisRecente, new Separator(Orientation.VERTICAL),
                horaMenosRecente, new Separator(Orientation.VERTICAL), numeroVeiculos);

        // Botoes
        Button btn = new Button("Atualizar");
        btn.setOnAction(x -> controle.atualizarTabela(x, data, barChart, pieChartData, ultimaLeituraServidor,
                horaMenosRecente, horaMaisRecente, numeroVeiculos, stage));
        botoes.getItems().addAll(btn);


        cenario.setCenter(table);
        cenario.setTop(botoes);
        cenario.setBottom(informacoes);
        cenario.setRight(graficos);
        cenario.setLeft(webView);

        stage.setMaximized(true);
        stage.setScene(new Scene(cenario, heigth, width));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

