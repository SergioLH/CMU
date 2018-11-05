package main.java.cmu;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Habitacion {
    private final IntegerProperty idHabitacion;
    private final StringProperty numeroTarjeta;
    private final StringProperty nombre;
    private final StringProperty apellidos;
    private final StringProperty piso;
    private final StringProperty numero;

    public Habitacion(int idHabitacion, String numeroTarjeta, String nombre, String apellido, String piso, String numero) {
        this.idHabitacion = new SimpleIntegerProperty(idHabitacion);
        this.piso = new SimpleStringProperty(piso);
        this.numero = new SimpleStringProperty(numero);
        this.numeroTarjeta = new SimpleStringProperty(numeroTarjeta);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellido);
    }

    public int getIdHabitacion() {
        return idHabitacion.get();
    }

    public IntegerProperty idHabitacionProperty() {
        return idHabitacion;
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
