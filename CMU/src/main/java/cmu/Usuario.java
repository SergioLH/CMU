package main.java.cmu;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    private final IntegerProperty idUsuario;
    private final StringProperty dni;
    private final StringProperty nombre;
    private final StringProperty apellidos;
    private final StringProperty tipoAlojamiento;
    private final StringProperty tipoUsuario;
    private final StringProperty telefono;
    private final StringProperty direccion;
    private final StringProperty sueldo;

    public Usuario(int idUsuario, String dni, String nombre, String apellidos, String tipoAlojamiento, String tipoUsuario,
                   String telefono, String direccion, String sueldo) {
        this.idUsuario = new SimpleIntegerProperty(idUsuario);
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.tipoAlojamiento = new SimpleStringProperty(tipoAlojamiento);
        this.tipoUsuario = new SimpleStringProperty(tipoUsuario);
        this.telefono = new SimpleStringProperty(telefono);
        this.direccion = new SimpleStringProperty(direccion);
        this.sueldo = new SimpleStringProperty(sueldo);
    }

    public int getIdUsuario() {
        return idUsuario.get();
    }

    public IntegerProperty idUsuarioProperty() {
        return idUsuario;
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

    public String getTipoAlojamiento() {
        return tipoAlojamiento.get();
    }

    public StringProperty tipoAlojamientoProperty() {
        return tipoAlojamiento;
    }

    public String getTipoUsuario() {
        return tipoUsuario.get();
    }

    public StringProperty tipoUsuarioProperty() {
        return tipoUsuario;
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public StringProperty sueldoProperty() {
        return sueldo;
    }

    public String getSueldo() {
        return sueldo.get();
    }
}
