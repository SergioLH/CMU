package main.java.cmu;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Historico {
    private final StringProperty numeroTarjeta;
    private final StringProperty lugar;
    private final StringProperty fecha;

    public Historico(String numeroTarjeta, String lugar, String fecha) {
        this.numeroTarjeta = new SimpleStringProperty(numeroTarjeta);
        this.lugar = new SimpleStringProperty(lugar);
        this.fecha = new SimpleStringProperty(fecha);
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta.get();
    }

    public StringProperty numeroTarjetaProperty() {
        return numeroTarjeta;
    }

    public String getLugar() {
        return lugar.get();
    }

    public StringProperty lugarProperty() {
        return lugar;
    }

    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }
}
