import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Aresta {
    public Vertice origem;
    public Vertice destino;
    public Line conexao;

    public Aresta(Vertice origem, Vertice destino, Line conexao, double grossuraConexao, Color corConexao) {
        this.origem = origem;
        this.destino = destino;
        this.conexao = conexao;
        this.conexao.setStrokeWidth(grossuraConexao);
        this.conexao.setStroke(corConexao);
    }

    public Line getConexao() {
        return conexao;
    }

    public void setConexaoFinal(double x, double y) {
        conexao.setEndX(x);
        conexao.setEndY(y);
    }

    public void setConexaoInit(double x, double y) {
        conexao.setStartX(x);
        conexao.setStartY(y);
    }

    public Vertice getOrigem() {
        return origem;
    }

    public Vertice getDestino() {
        return destino;
    }
}
