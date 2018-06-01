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

    public Circle getRepresetancao() {
        return represetancao;
    }

    public LinkedList<Aresta> getLigacoes() {
        return ligacoes;
    }

    // Adiciona uma ligacao
    void addLigacoes(Aresta e) {
        ligacoes.add(e);
    }

    boolean jaLigados(Vertice v) {
        for (Aresta a: ligacoes) {
            if (a.getDestino() == v || a.getOrigem() == v) {
                return true;
            }
        }
        return false;
    }
}
