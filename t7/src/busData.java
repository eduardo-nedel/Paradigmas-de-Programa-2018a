import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class busData {
    private SimpleStringProperty dataHora;
    private SimpleStringProperty ordem;
    private SimpleStringProperty linha;
    private SimpleStringProperty latitude;
    private SimpleStringProperty longitude;
    private SimpleDoubleProperty velocidade;

    public busData(String dataHora, String ordem, String linha, String latitude, String longitude, Double velocidade) {
        this.dataHora = new SimpleStringProperty();
        this.ordem = new SimpleStringProperty();
        this.linha = new SimpleStringProperty();
        this.latitude = new SimpleStringProperty();
        this.longitude = new SimpleStringProperty();
        this.velocidade = new SimpleDoubleProperty();

        this.dataHora.set(dataHora);
        this.ordem.set(ordem);
        this.linha.set(linha);
        this.latitude.set(latitude);
        this.longitude.set(longitude);
        this.velocidade.set(velocidade);
    }

    public String getDataHora() {
        return dataHora.get();
    }

    public SimpleStringProperty dataHoraProperty() {
        return dataHora;
    }

    public String getOrdem() {
        return ordem.get();
    }

    public SimpleStringProperty ordemProperty() {
        return ordem;
    }

    public String getLinha() {
        return linha.get();
    }

    public SimpleStringProperty linhaProperty() {
        return linha;
    }

    public String getLatitude() {
        return latitude.get();
    }

    public SimpleStringProperty latitudeProperty() {
        return latitude;
    }

    public String getLongitude() {
        return longitude.get();
    }

    public SimpleStringProperty longitudeProperty() {
        return longitude;
    }

    public Double getVelocidade() {
        return velocidade.get();
    }

    public SimpleDoubleProperty velocidadeProperty() {
        return velocidade;
    }
}
