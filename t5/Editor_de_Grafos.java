import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Editor_de_Grafos extends Application {

    Grafo grafo = new Grafo();
    Vertice inicio_linha;
    private Line linha;
    boolean tracandoLinha;
    int contGrafos;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;


    @Override
    public void start(Stage stage) throws Exception {
        tracandoLinha = false;
        contGrafos = 1;

        stage.setTitle("Editor de Grafos");

        // Formato do Programa
        BorderPane cenario = new BorderPane();
        HBox contadores = new HBox();
        contadores.setStyle("-fx-border-color: #c9c9c9;" + "-fx-border-width: 1;" + "-fx-background-color: #f2f2f2;" +
                "-fx-border-style: solid hidden hidden hidden");
        VBox menu = new VBox();
        ToolBar personalizacao = new ToolBar();
        personalizacao.setOrientation(Orientation.VERTICAL);
        Pane area_de_desenho = new Pane();

        // Contadores
        Label contVertice = new Label( ' ' + "Vertices: " + grafo.getContadorVertices() + ' ');
        Label contArestas = new Label("Arestas: " + grafo.getContadorArestas() + ' ');
        Label contArestaSobreposta = new Label("Arestas Sobrepostas: " +  grafo.getIntercecoes() + ' ');
        contadores.setAlignment(Pos.BOTTOM_LEFT);
        contadores.getChildren().addAll(contVertice, new Separator(Orientation.VERTICAL), contArestas,
                new Separator(Orientation.VERTICAL), contArestaSobreposta, new Separator(Orientation.VERTICAL));

        // Menu
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Arquivo");
        MenuItem novo = new MenuItem("Criar Novo Grafo");
        novo.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                area_de_desenho.getChildren().clear();
                grafo.resetaGrafo();
                contVertice.setText(' ' + "Vertices: " + grafo.getContadorVertices() + ' ');
                contArestas.setText("Arestas: " + grafo.getContadorArestas() + ' ');
                contArestaSobreposta.setText("Arestas Sobrepostas: " +  grafo.getIntercecoes() + ' ');
            }
        });

        MenuItem salvar = new MenuItem("Salvar o Grafo em SVG");
        salvar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                File f = new File("grafo"+ contGrafos +".html");
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                    bw.write(SVGgenerator.getSVG(grafo, WIDTH, HEIGHT));
                    bw.close();
                    contGrafos+=1;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        MenuItem sair = new MenuItem("Sair");
        sair.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                stage.close();
            }
        });

        menuFile.getItems().addAll(novo, salvar, new SeparatorMenuItem(), sair);
        menuBar.getMenus().addAll(menuFile);
        menu.getChildren().addAll(menuBar);


        // Personalizacao
        final ToggleGroup figura = new ToggleGroup();
        ToggleButton aresta = new ToggleButton("Aresta");
        ToggleButton vertice = new ToggleButton("Vértice");
        aresta.setToggleGroup(figura);
        vertice.setToggleGroup(figura);
        vertice.setSelected(true);

        Label corVertice = new Label("Cor do Vértice");
        ColorPicker seletorCorV = new ColorPicker();
        seletorCorV.setValue(Color.ORANGE);

        Label corBorda = new Label("Cor da Borda");
        ColorPicker seletorCorB = new ColorPicker();
        seletorCorB.setValue(Color.LIGHTGRAY);

        Label raio = new Label("Raio do Vértice");

        Slider seletorTamanhoV = new Slider();
        seletorTamanhoV.setMin(10);
        seletorTamanhoV.setMax(40);
        seletorTamanhoV.setValue(10);
        seletorTamanhoV.setShowTickLabels(true);
        seletorTamanhoV.setShowTickMarks(true);
        seletorTamanhoV.setMajorTickUnit(10);
        seletorTamanhoV.setMinorTickCount(5);
        seletorTamanhoV.setBlockIncrement(10);

        Label corLinha = new Label("Cor da Aresta");
        ColorPicker seletorCorA = new ColorPicker();
        seletorCorA.setValue(Color.BLACK);

        Label grossura = new Label("Grossura da Aresta");

        Slider seletorTamanhoA = new Slider();
        seletorTamanhoA.setMin(1);
        seletorTamanhoA.setMax(10);
        seletorTamanhoA.setValue(1);
        seletorTamanhoA.setShowTickLabels(true);
        seletorTamanhoA.setShowTickMarks(true);
        seletorTamanhoA.setMajorTickUnit(1);
        seletorTamanhoA.setMinorTickCount(5);
        seletorTamanhoA.setBlockIncrement(10);

        personalizacao.getItems().addAll(vertice, corVertice, seletorCorV, corBorda,
                seletorCorB, raio, seletorTamanhoV, new Separator(), aresta, corLinha, seletorCorA, grossura, seletorTamanhoA);

        area_de_desenho.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if(figura.getSelectedToggle() == vertice) {
                    if (grafo.foraVertice(e.getX(), e.getY(), seletorTamanhoV.getValue())) {
                        grafo.addVertice(e.getX(), e.getY(), seletorTamanhoV.getValue(), seletorCorV.getValue(), seletorCorB.getValue());
                        area_de_desenho.getChildren().add(grafo.getLastVertice().getRepresetancao());
                        contVertice.setText(' ' + "Vertices: " + grafo.getContadorVertices() + ' ');
                    }
                }
                else {
                    // Verifica se um ponto está dentro de um vértice
                    if(!grafo.foraVertice(e.getX(), e.getY(), 0)) {
                        inicio_linha = grafo.getVerticeMouse(e.getX(), e.getY());
                        linha = new Line(inicio_linha.getRepresetancao().getCenterX(), inicio_linha.getRepresetancao().getCenterY(), e.getX(), e.getY());
                        linha.setStroke(seletorCorA.getValue());
                        linha.setStrokeWidth(seletorTamanhoA.getValue());
                        area_de_desenho.getChildren().add(0, linha);
                        tracandoLinha = true;
                    }
                }
            }
        });

        area_de_desenho.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (figura.getSelectedToggle() == aresta && tracandoLinha) {
                    linha.setEndX(e.getX());
                    linha.setEndY(e.getY());
                }
            }
        });

        area_de_desenho.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                if (figura.getSelectedToggle() == aresta && tracandoLinha) {
                    if (!grafo.foraVertice(e.getX(), e.getY(), 0) &&  !grafo.getVerticeMouse(e.getX(), e.getY()).equals(inicio_linha) &&
                            !inicio_linha.jaLigados(grafo.getVerticeMouse(e.getX(), e.getY()))) {
                        Vertice temp = grafo.getVerticeMouse(e.getX(), e.getY());
                        linha.setEndX(temp.getRepresetancao().getCenterX());
                        linha.setEndY(temp.getRepresetancao().getCenterY());
                        grafo.addAresta(inicio_linha, temp, linha, seletorTamanhoA.getValue(), seletorCorA.getValue());
                        contArestas.setText("Arestas: " + grafo.getContadorArestas() + ' ');
                        contArestaSobreposta.setText("Arestas Sobrepostas: " +  grafo.getIntercecoes() + ' ');
                    }
                    else
                        area_de_desenho.getChildren().remove(linha);
                    tracandoLinha = false;
                }
            }
        });


        cenario.setCenter(area_de_desenho);
        cenario.setTop(menu);
        cenario.setBottom(contadores);
        cenario.setRight(personalizacao);

        Scene scene = new Scene(cenario, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
