import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class Data {
    private List dados;

    LinkedList<busData> l = new LinkedList<busData>();

    public boolean NewData(boolean lerWeb, File arquivo) {
        l = new LinkedList<busData>();
        Map json = null;
        if (lerWeb) {
            HttpJSONService http = new HttpJSONService();
            try {
                json = http.sendGet("http://dadosabertos.rio.rj.gov.br/apiTransporte/apresentacao/rest/index.cfm/obterTodasPosicoes");
            } catch (Exception e) {
                return false;
            }
        }
        else {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo));
                json = (new JSONParsing().parseJSON(bufferedReader.readLine()));
            } catch (Exception ex) {
                return false;
            }
        }

        if (json != null) {
            SeparaDados((List)json.get("DATA"));
        }
        return true;
    }

    public void SeparaDados(List lista) {
        dados = lista;
        for (int i = 0; i < dados.size(); i++) {
            l.add(new busData(
                    ((List)dados.get(i)).get(0).toString(),
                    ((List)dados.get(i)).get(1).toString(),
                    ((List)dados.get(i)).get(2).toString(),
                    ((List)dados.get(i)).get(3).toString(),
                    ((List)dados.get(i)).get(4).toString(),
                    Double.parseDouble(((List)dados.get(i)).get(5).toString())
            ));
        }
    }
}
