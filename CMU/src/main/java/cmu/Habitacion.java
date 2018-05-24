package main.java.cmu;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Habitacion {
    private StringProperty dni;
    private StringProperty nombre;
    private StringProperty apellidos;
    private StringProperty piso;
    private StringProperty numero;

    public Habitacion(String dni, String nombre, String apellido, String piso, String numero) {
        this.piso = new SimpleStringProperty(piso);
        this.numero = new SimpleStringProperty(numero);
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellido);
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

    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
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
