/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vista;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Seryak
 */
@SuppressWarnings("ALL")
public class RegistrarController {

    @FXML
    private JFXButton botonRegistrar;

    @FXML
    private JFXButton botonVolverMenu;

    @FXML
    private JFXTextField campoDNI;

    @FXML
    private JFXTextField campoNombre;

    @FXML
    private JFXTextField campoApellidos;

    @FXML
    private JFXTextField campoTelefono;

    @FXML
    private JFXTextField campoDireccion;

    @FXML
    private JFXTextField campoTipoAlojamiento;

    @FXML
    private JFXTextField campoRol;

    @FXML
    private JFXTextField campoSueldo;

    @FXML
    private void initialize() {

    }

    @FXML
    private void botonRegistrar() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                registrar();
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("MenuEncargado.fxml"));
                    try {
                        escena.setRoot(loader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                return null;
            }
        };
        new Thread(tarea).start();
    }

    private void registrar() {
        final String dni = campoDNI.getText();
        final String nombre = campoNombre.getText();
        final String apellidos = campoApellidos.getText();
        final String telefono = campoTelefono.getText();
        final String direccion = campoDireccion.getText();
        final String tipoAlojamiento = campoTipoAlojamiento.getText();
        final String rol = campoRol.getText();
        final String sueldo = campoSueldo.getText();

        //METODO DE AÑADIR INVITADO -> SUPONGO QUE SERA HACER EL JSON PARA PASAR EL INVITADO AL SERVER?
    }

    @FXML
    private void botonVolverMenu() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("MenuEncargado.fxml"));
                    try {
                        escena.setRoot(loader.load());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                return null;
            }
        };
        new Thread(tarea).start();
    }
}
