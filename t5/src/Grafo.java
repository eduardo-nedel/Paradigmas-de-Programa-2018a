import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Grafo {
    private LinkedList<Vertice> vertices;
    private LinkedList<Aresta> arestas;
    private int contadorVertices;
    private int contadorArestas;

    public Grafo() {
        vertices = new LinkedList<Vertice>();
        arestas = new LinkedList<Aresta>();
        contadorVertices = 0;
        contadorArestas = 0;
    }

    // Adiciona um Vertice
    public void addVertice(double x, double y, double raio, Color corCirculo, Color corBorda) {
        vertices.add(new Vertice(x, y, raio, corCirculo, corBorda));
        contadorVertices+=1;
    }

    // Adiciona uma Aresta
    public void addAresta(Vertice origem, Vertice destino, Line linha, double grossuraLinha, Color corLinha) {
        Aresta e = new Aresta(origem, destino, linha, grossuraLinha, corLinha);
        origem.addLigacoes(e);
        destino.addLigacoes(e);
        arestas.add(e);
        contadorArestas+=1;
    }

    // Calcula a distancia entre um ponto e um circulo
    private double distancia(double x, double y, Circle c) {
        return Math.hypot(x - c.getCenterX(), y - c.getCenterY());
    }

    // Verifica se um vertice não encosta em outro
    public boolean foraVertice(double x, double y, double raio) {
        boolean teste = true;
        for (Vertice v : vertices) {
            if (distancia(x, y, v.getRepresetancao()) <= (v.getRepresetancao().getRadius() + raio)) {
                teste = false;
            }
        }
        return teste;
    }

    // Retorna um vertice que contem o ponto (x, y)
    public Vertice getVerticeMouse(double x, double y) {
        for (Vertice v : vertices) {
            if (distancia(x, y, v.getRepresetancao()) <= v.getRepresetancao().getRadius()) {
                return v;
            }
        }
        return null;
    }

    // Retorna o numero de intercecoes entre as arestas
    public int getIntercecoes() {
        int contador = 0;
        for (Aresta a:arestas) {
            for (Aresta b:arestas) {
                if (a.getOrigem() == b.getOrigem() || a.getOrigem() == b.getDestino() || a.getDestino() == b.getOrigem() ||
                        a.getDestino() == b.getDestino());
                else {
                    if (Line2D.linesIntersect(a.getOrigem().getRepresetancao().getCenterX(),
                            a.getOrigem().getRepresetancao().getCenterY(),
                            a.getDestino().getRepresetancao().getCenterX(),
                            a.getDestino().getRepresetancao().getCenterY(),
                            b.getOrigem().getRepresetancao().getCenterX(),
                            b.getOrigem().getRepresetancao().getCenterY(),
                            b.getDestino().getRepresetancao().getCenterX(),
                            b.getDestino().getRepresetancao().getCenterY())){
                        contador+=1;
                    }
                }
            }
        }
        return contador/2;
    }

    // Retorna o último vértice
    public Vertice getLastVertice() {
        return vertices.getLast();
    }

    public LinkedList<Vertice> getVertices() {
        return vertices;
    }

    public LinkedList<Aresta> getArestas() {
        return arestas;
    }

    public int getContadorVertices() {
        return contadorVertices;
    }

    public int getContadorArestas() {
        return contadorArestas;
    }

    // Reinicia o grafo, deixando-o zerado
    public void resetaGrafo() {
        vertices.clear();
        arestas.clear();
        vertices = new LinkedList<Vertice>();
        arestas = new LinkedList<Aresta>();
        contadorVertices = 0;
        contadorArestas = 0;
    }
}
