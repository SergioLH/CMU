package main.java.cmu;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Historico {
    private StringProperty dni;
    private StringProperty lugar;
    private StringProperty fecha;

    public Historico(String dni, String lugar, String fecha) {
        this.dni = new SimpleStringProperty(dni);
        this.lugar = new SimpleStringProperty(lugar);
        this.fecha = new SimpleStringProperty(fecha);
    }

    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
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
