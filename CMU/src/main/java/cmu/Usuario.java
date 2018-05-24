package main.java.cmu;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    private StringProperty dni;
    private StringProperty nombre;
    private StringProperty apellidos;
    private StringProperty tipoAlojamiento;
    private StringProperty tipoUsuario;
    private StringProperty  telefono;
    private StringProperty direccion;
    private StringProperty sueldo;

    public Usuario(String dni, String nombre, String apellidos, String tipoAlojamiento, String tipoUsuario,
                   String telefono, String direccion, String sueldo) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
        this.tipoAlojamiento = new SimpleStringProperty(tipoAlojamiento);
        this.tipoUsuario = new SimpleStringProperty(tipoUsuario);
        this.telefono = new SimpleStringProperty(telefono);
        this.direccion = new SimpleStringProperty(direccion);
        this.sueldo = new SimpleStringProperty(sueldo);
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
