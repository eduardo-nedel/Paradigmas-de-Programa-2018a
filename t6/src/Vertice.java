import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.LinkedList;


public class Vertice {
    private Circle represetancao;
    private LinkedList<Aresta> ligacoes;

    public Vertice(double x, double y, double raio, Color corCirculo, Color corBorda) {
        represetancao = new Circle(x, y, raio-raio/5, corCirculo);
        represetancao.setStrokeWidth(raio/5);
        represetancao.setStroke(corBorda);
        ligacoes = new LinkedList<Aresta>();
    }

    public void setAxis(double x, double y) {
        represetancao.setCenterX(x);
        represetancao.setCenterY(y);
    }

    public void setPosAresta(double x, double y) {
        for (Aresta a: ligacoes) {
            if (a.destino == this) {
                a.setConexaoFinal(x, y);
            }
            else if (a.origem == this) {
                a.setConexaoInit(x, y);
            }
        }
    }

    public int numAresta() {
        return ligacoes.size();
    }

    public Circle getRepresetancao() {
        return represetancao;
    }

    // Adiciona uma ligacao
    void addLigacoes(Aresta e) {
        ligacoes.add(e);
    }

    // Descobre se dois vertices ja estao ligados por uma aresta
    boolean jaLigados(Vertice v) {
        for (Aresta a: ligacoes) {
            if (a.getDestino() == v || a.getOrigem() == v) {
                return true;
            }
        }
        return false;
    }
}
