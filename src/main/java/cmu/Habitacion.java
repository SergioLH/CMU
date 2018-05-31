package main.java.cmu;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Habitacion {
    private IntegerProperty id;
    private StringProperty numeroTarjeta;
    private StringProperty nombre;
    private StringProperty apellidos;
    private StringProperty piso;
    private StringProperty numero;

    public Habitacion(int id, String numeroTarjeta, String nombre, String apellido, String piso, String numero) {
        this.id = new SimpleIntegerProperty(id);
        this.piso = new SimpleStringProperty(piso);
        this.numero = new SimpleStringProperty(numero);
        this.numeroTarjeta = new SimpleStringProperty(numeroTarjeta);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellido);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getPiso() {
        return piso.get();
    }

    public StringProperty pisoProperty() {
        return piso;
    }

    public String getNumero() {
        return numero.get();
    }

    public StringProperty numeroProperty() {
        return numero;
    }

    public String getNumeroTarejta() {
        return numeroTarjeta.get();
    }

    public StringProperty numeroTarjetaProperty() {
        return numeroTarjeta;
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos.get();
    }

    public StringProperty apellidosProperty() {
        return apellidos;
    }
}
