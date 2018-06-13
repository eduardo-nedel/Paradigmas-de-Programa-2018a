import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Random;

public class Editor_de_Grafos extends Application {

    private void liga_aresta(Vertice v, Pane area_de_desenho) {
        LinkedList<Vertice> vertices = grafo.getVertices();
        linha = new Line(vertices.get(vertices.size() - 2).getRepresetancao().getCenterX(), vertices.get(vertices.size() - 2).getRepresetancao().getCenterY(),
                vertices.get(vertices.size() - 1).getRepresetancao().getCenterX(), vertices.get(vertices.size() - 1).getRepresetancao().getCenterY());
        area_de_desenho.getChildren().add(0, linha);
        grafo.addAresta(vertices.get(vertices.size() - 2), vertices.get(vertices.size() -1), linha, 1, Color.BLACK);
        linha = new Line(vertices.get(vertices.size() - 3).getRepresetancao().getCenterX(), vertices.get(vertices.size() - 3).getRepresetancao().getCenterY(),
                vertices.get(vertices.size() - 1).getRepresetancao().getCenterX(), vertices.get(vertices.size() - 1).getRepresetancao().getCenterY());
        area_de_desenho.getChildren().add(0, linha);
        grafo.addAresta(vertices.get(vertices.size() - 3), vertices.get(vertices.size() - 1), linha, 1, Color.BLACK);
    }

    // Avanca de fase, apagando tudo que temos, criando outro grafo, incrementando o número da fase.
    private void avanca_fase(Pane area_de_desenho, Label contFase, Label contArestaSobreposta) {
        area_de_desenho.getChildren().clear();
        grafo.resetaGrafo();
        fase++;
        contFase.setText("FASE: " +  fase + ' ');
        cria_grafo_planar(area_de_desenho);
        contArestaSobreposta.setText("Arestas Sobrepostas: " +  grafo.getIntercecoes() + ' ');
    }

    // Cria um grafo planar, através da triangulação
    private void cria_grafo_planar(Pane area_de_desenho) {
        temp = new Vertice(new Random().nextDouble()*WIDTH, new Random().nextDouble()*(HEIGHT-60), 10, Color.ORANGE, Color.ORANGE);
        temp2 = new Vertice(new Random().nextDouble()*WIDTH, new Random().nextDouble()*(HEIGHT-60), 10, Color.ORANGE, Color.ORANGE);
        grafo.addVertice(temp);
        grafo.addVertice(temp2);
        linha = new Line(temp.getRepresetancao().getCenterX(), temp.getRepresetancao().getCenterY(),
                temp2.getRepresetancao().getCenterX(), temp2.getRepresetancao().getCenterY());
        grafo.addAresta(temp, temp2, linha, 1, Color.BLACK);
        area_de_desenho.getChildren().addAll(temp.getRepresetancao(), temp2.getRepresetancao());
        area_de_desenho.getChildren().add(0, linha);
        // Cria um grafo planar, porém com um número específico de arestas, que sempre irá respeitar a planaridade
        for (int x = 0; x < fase + 4; x++) {
            temp = new Vertice(new Random().nextDouble()*WIDTH, new Random().nextDouble()*(HEIGHT-60), 10, Color.ORANGE, Color.ORANGE);
            grafo.addVertice(temp);
            liga_aresta(temp, area_de_desenho);
            area_de_desenho.getChildren().add(temp.getRepresetancao());
        }
    }

    Grafo grafo = new Grafo();
    Vertice verticeMovido;
    Vertice temp;
    Vertice temp2;
    private Line linha;
    boolean movendoVertice;
    int fase;
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage stage) throws Exception {
        movendoVertice = false;
        fase = 1;

        stage.setTitle("Solucione os Grafos");

        // Formato do Programa
        BorderPane cenario = new BorderPane();
        HBox contadores = new HBox();
        contadores.setStyle("-fx-border-color: #c9c9c9;" + "-fx-border-width: 1;" + "-fx-background-color: #f2f2f2;" +
                "-fx-border-style: solid hidden hidden hidden");
        ToolBar personalizacao = new ToolBar();
        Pane area_de_desenho = new Pane();

        // Contadores
        Label contArestaSobreposta = new Label("Arestas Sobrepostas: " +  grafo.getIntercecoes() + ' ');
        Label contFase = new Label("FASE: " +  fase + ' ');
        contArestaSobreposta.setAlignment(Pos.TOP_RIGHT);
        contadores.getChildren().addAll(contArestaSobreposta, new Separator(Orientation.VERTICAL), contFase);

        // Menu
        Button proxima_fase = new Button("Proxima Fase");
        proxima_fase.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if (grafo.getIntercecoes() == 0) {
                    avanca_fase(area_de_desenho, contFase, contArestaSobreposta);
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Impossível Avançar");
                    alerta.setHeaderText("Ainda existem interceções entre as arestas do grafo. Resolva-as e tente novamente!");

                    alerta.showAndWait();
                }
            }
        });

        Button forca_avanca_fase = new Button("Forcar avanco de fase");
        forca_avanca_fase.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                avanca_fase(area_de_desenho, contFase, contArestaSobreposta);
            }
        });

        personalizacao.getItems().addAll(proxima_fase, forca_avanca_fase);

        // Cria o primeiro grafo
        cria_grafo_planar(area_de_desenho);

        // Inicia o contador de Arestas Sobrepostas
        contArestaSobreposta.setText("Arestas Sobrepostas: " +  grafo.getIntercecoes() + ' ');

        // Movimentos
        area_de_desenho.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                // Verifica se um ponto está dentro de um vértice e caso esteja, coloca a variavel de movimento de vertice para verdadeiro
                if(!grafo.foraVertice(e.getX(), e.getY(), 0)) {
                    verticeMovido = grafo.getVerticeMouse(e.getX(), e.getY());
                    movendoVertice = true;
                }
            }
        });

        // Move vertice enquanto o mouse estiver pressionado
        area_de_desenho.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (movendoVertice && ( e.getY() < HEIGHT-60 && e.getY() > 0) && (e.getX() < WIDTH && e.getX() > 0)) {
                    verticeMovido.setAxis(e.getX(), e.getY());
                    verticeMovido.setPosAresta(e.getX(), e.getY());
                }
                contArestaSobreposta.setText("Arestas Sobrepostas: " +  grafo.getIntercecoes() + ' ');
            }
        });

        // Quando o mouse deixa de ser pressionado, coloca a variavel de movimento de Vertice para falso
        area_de_desenho.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                movendoVertice = false;
            }
        });

        // Adiciona os elementos ao visual do programa
        cenario.setCenter(area_de_desenho);
        cenario.setTop(personalizacao);
        cenario.setBottom(contadores);

        Scene scene = new Scene(cenario, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
