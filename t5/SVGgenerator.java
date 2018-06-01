import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.LinkedList;

public class SVGgenerator {

    // Aqui transformamos um stroke em uma string hexadecimal
    // A base deste código foi retirado do link https://stackoverflow.com/questions/20532891/javafx-get-rgb-value-from-nodes-color-fill
    public static String generateColor(Paint corStroke) {
        Color c = Color.valueOf(corStroke.toString());
        return String.format( "#%02X%02X%02X",
                (int)( c.getRed() * 255 ),
                (int)( c.getGreen() * 255 ),
                (int)( c.getBlue() * 255 ) );
    }

    public static String generateStart(double width, double height) {
        return "<html>\n<body>\n\n<svg width=\" " + width + " \" height=\"" + height + "\">";
    }

    public static String generateEnd() {
        return "</svg>\n\n</body>\n</html>";
    }

    public static String generateCircle(Circle c) {
        return "<circle cx=\"" + c.getCenterX() + "\" cy=\"" + c.getCenterY() + "\" r=\"" + c.getRadius()
                + "\" stroke=\"" + generateColor(c.getStroke()) + "\" stroke-width=\"" + c.getStrokeWidth() + "\" fill=\"" + generateColor(c.getFill()) + "\" />";
    }

    public static String generateLine(Line l) {
        return "<line x1=\" " + l.getStartX() + "\" y1=\" " + l.getStartY() + " \" x2=\" " + l.getEndX() + " \" y2=\" " + l.getEndY() +
                " \" style=\"stroke:" + generateColor(l.getStroke()) + ";stroke-width:" + l.getStrokeWidth() + "\" />";
    }

    public static String getSVG(Grafo grafo, double width, double height) {
        String resultado = generateStart(width, height);
        LinkedList<Aresta> arestas = grafo.getArestas();
        LinkedList<Vertice> vertices = grafo.getVertices();

        resultado+='\n';
        for (Aresta a:arestas) {
            resultado+= generateLine(a.getConexao());
            resultado+='\n';
        }

        resultado+='\n';

        for (Vertice v:vertices) {
            resultado+= generateCircle(v.getRepresetancao());
            resultado+='\n';
        }

        resultado+='\n';

        resultado+= generateEnd();

        return resultado;
    }
}
