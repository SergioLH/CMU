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
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    private String tipo;

    public void setTipo(String tipo) {
        this.tipo = tipo;
        CargarTipoUsuario();
    }

    private void CargarTipoUsuario() {

        switch (this.tipo) {
            case "Residente":
                campoRol.setVisible(false);
                campoSueldo.setVisible(false);
                break;
            case "Invitado":
                campoTelefono.setVisible(false);
                campoDireccion.setVisible(false);
                campoTipoAlojamiento.setVisible(false);
                campoRol.setVisible(false);
                campoSueldo.setVisible(false);
                break;
            case "Trabajador":
                campoTelefono.setVisible(false);
                campoDireccion.setVisible(false);
                campoTipoAlojamiento.setVisible(false);
                break;
        }
    }

    @FXML
    private void initialize() {
        campoTelefono.managedProperty().bindBidirectional(campoTelefono.visibleProperty());
        campoDireccion.managedProperty().bindBidirectional(campoDireccion.visibleProperty());
        campoTipoAlojamiento.managedProperty().bindBidirectional(campoTipoAlojamiento.visibleProperty());
        campoRol.managedProperty().bindBidirectional(campoRol.visibleProperty());
        campoSueldo.managedProperty().bindBidirectional(campoSueldo.visibleProperty());
    }

    @FXML
    private void botonRegistrar() throws IOException {
        registrar();
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                registrar();
                if (registrar() == true) {
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
                }
                return null;
            }
        };
        tarea.setOnFailed(event -> {
            event.getSource().getException().printStackTrace();
        });
        new Thread(tarea).start();
    }

    private boolean registrar() throws IOException {
        final String dni = campoDNI.getText();
        final String nombre = campoNombre.getText();
        final String apellidos = campoApellidos.getText();
        final String telefono = campoTelefono.getText();
        final String direccion = campoDireccion.getText();
        final String tipoAlojamiento = campoTipoAlojamiento.getText();
        final String rol = campoRol.getText();
        final String sueldo = campoSueldo.getText();

        if (tipo == "Residente") {
            if (!(campoDNI.getText().isEmpty()) && !(campoNombre.getText().isEmpty())
                    && !(campoApellidos.getText().isEmpty()) && !(campoTelefono.getText().isEmpty())
                    && !(campoDireccion.getText().isEmpty()) && !(campoTipoAlojamiento.getText().isEmpty())) {
                URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/RegistroCompleto");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                if (connection.getResponseCode() != 200) {
                    throw new RuntimeException("Error: HTTP codigo error: " + connection.getResponseCode());
                }
                JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(connection.getInputStream()));
                JSONObject jsonObject = new JSONObject(jsonTokener);
                System.out.println(jsonObject.get("mensaje"));
                connection.disconnect();
                return true;
            } else {
                URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/MensajeError");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                if (connection.getResponseCode() != 200) {
                    throw new RuntimeException("Error: HTTP codigo error: " + connection.getResponseCode());
                }
                JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(connection.getInputStream()));
                JSONObject jsonObject = new JSONObject(jsonTokener);
                System.out.println(jsonObject.get("mensaje"));
                connection.disconnect();
                return false;
            }
        } else if (tipo == "Trabajador") {
            if (!(campoDNI.getText().isEmpty()) && !(campoNombre.getText().isEmpty())
                    && !(campoApellidos.getText().isEmpty()) && !(campoRol.getText().isEmpty())
                    && !(campoSueldo.getText().isEmpty())) {
                URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/RegistroCompleto");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                if (connection.getResponseCode() != 200) {
                    throw new RuntimeException("Error: HTTP codigo error: " + connection.getResponseCode());
                }
                JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(connection.getInputStream()));
                JSONObject jsonObject = new JSONObject(jsonTokener);
                System.out.println(jsonObject.get("mensaje"));
                connection.disconnect();
                return true;
            } else {
                URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/MensajeError");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                if (connection.getResponseCode() != 200) {
                    throw new RuntimeException("Error: HTTP codigo error: " + connection.getResponseCode());
                }
                JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(connection.getInputStream()));
                JSONObject jsonObject = new JSONObject(jsonTokener);
                System.out.println(jsonObject.get("mensaje"));
                connection.disconnect();
                return false;
            }
        } else if (tipo == "Invitado") {
            if (!(campoDNI.getText().isEmpty()) && !(campoNombre.getText().isEmpty())
                    && !(campoApellidos.getText().isEmpty())) {
                URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/RegistroCompleto");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                if (connection.getResponseCode() != 200) {
                    throw new RuntimeException("Error: HTTP codigo error: " + connection.getResponseCode());
                }
                JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(connection.getInputStream()));
                JSONObject jsonObject = new JSONObject(jsonTokener);
                System.out.println(jsonObject.get("mensaje"));
                connection.disconnect();
                return true;
            } else {
                URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/MensajeError");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                if (connection.getResponseCode() != 200) {
                    throw new RuntimeException("Error: HTTP codigo error: " + connection.getResponseCode());
                }
                JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(connection.getInputStream()));
                JSONObject jsonObject = new JSONObject(jsonTokener);
                System.out.println(jsonObject.get("mensaje"));
                connection.disconnect();
                return false;
            }
        } else {
            return false;
        }
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
        tarea.setOnFailed(event -> {
            event.getSource().getException().printStackTrace();
        });
        new Thread(tarea).start();
    }
}
