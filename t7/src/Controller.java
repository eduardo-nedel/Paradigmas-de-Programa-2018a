import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


public class Controller {
    public Data dados = new Data();


    public void atualizarTabela(ActionEvent event, ObservableList<busData> data, BarChart<String, Number> barChart, ObservableList<PieChart.Data> pieChartData,
                       Label UltimaLeitura, Label dataRecente, Label dataAntiga, Label numVeiculos, Stage stage) {
        dados.l.clear();
        UltimaLeitura.setText(" Última leitura de dados:  " + new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) + ' ');

        if (dados.NewData(true, null)) {
            insereDadosNaObservableList(data, dados.l);
            atualizaDados(dados.l, barChart, pieChartData, dataRecente, dataAntiga, numVeiculos);
        }
        // Se der erro ao atualizar a tabela online
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Falha");
            alert.setContentText("Escolha um Arquivo Válido ou Conecte-se a Internet!");
            alert.showAndWait();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JSON", "*.json"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (dados.NewData(false, selectedFile)) {
                insereDadosNaObservableList(data, dados.l);
                atualizaDados(dados.l, barChart, pieChartData, dataRecente, dataAntiga, numVeiculos);
            }
        }
    }

    public void insereDadosNaObservableList(ObservableList<busData> data, LinkedList<busData> list) {
        data.clear();
        for (busData d : list) {
            data.add(d);
        }
    }

    public void atualizaDados(LinkedList<busData> list, BarChart<String, Number> barChart, ObservableList<PieChart.Data> pieChartData,
                              Label dataRecente, Label dataAntiga, Label numVeiculos) {
        //Grafico Torta
        int andando = 0;

        for (busData d : list) {
            if (d.getVelocidade() != 0) {
                andando++;
            }
        }
        pieChartData.get(0).setPieValue(list.size()-andando);
        pieChartData.get(1).setPieValue(andando);

        // Grafico de Barras

        XYChart.Series series = new XYChart.Series();
        for(String linha: getLinhas(list)) {
            series.getData().add(new XYChart.Data(linha, getBusMovimentoLinha(linha, list)));
        }
        barChart.getData().clear();
        barChart.getData().add(series);

        // Atualiza Textos
        dataRecente.setText("Data-Hora Mais Recente : " + list.getLast().getDataHora() + ' ');
        dataAntiga.setText("Data-Hora Menos Recente : " + list.getFirst().getDataHora() + ' ');
        numVeiculos.setText("Numero de Veiculos : " + list.size() + ' ');
    }

    public LinkedList<String> getLinhas(LinkedList<busData> lista) {
        LinkedList<String> list = new LinkedList<>();
        for (busData d: lista) {
            if (!list.contains(d.getLinha()) && !d.getLinha().isEmpty()) {
                list.add(d.getLinha());
            }
        }
        return list;
    }

    public int getBusMovimentoLinha(String linha, LinkedList<busData> lista) {
        int emMovimento = 0;
        for (busData d: lista) {
            if (d.getLinha().equals(linha) && d.getVelocidade() != 0) {
                emMovimento++;
            }
        }
        return emMovimento;
    }
}