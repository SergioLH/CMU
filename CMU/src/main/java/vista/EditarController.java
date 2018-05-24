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

@SuppressWarnings("ALL")
public class EditarController {

    @FXML
    private JFXButton botonEditar;

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
    private void botonEditar() {

        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                editar();
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("ListarUsuarios.fxml"));
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

    private void editar() {
        final String dni = campoDNI.getText();
        final String nombre = campoNombre.getText();
        final String apellidos = campoApellidos.getText();
        final String telefono = campoTelefono.getText();
        final String direccion = campoDireccion.getText();
        final String tipoAlojamiento = campoTipoAlojamiento.getText();
        final String rol = campoRol.getText();
        final String sueldo = campoSueldo.getText();

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
