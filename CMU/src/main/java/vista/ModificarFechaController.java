package main.java.vista;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;


import java.time.format.DateTimeFormatter;

@SuppressWarnings("ALL")
public class ModificarFechaController {

    @FXML
    private JFXButton botonVolverMenu;

    @FXML
    private JFXButton botonConfirmar;

    @FXML
    private JFXTextField campoTarjeta;

    @FXML
    private JFXDatePicker campoNuevaFecha;

    @FXML
    private void initialize() {

    }

    @FXML
    private void botonConfirmar() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                confirmar();
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("MenuEncargado.fxml"));

                    try {
                        escena.setRoot(loader.load());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                return null;
            }
        };
        new Thread(tarea).start();
    }

    private void confirmar() {
        final String numeroTarjeta = campoTarjeta.getText();
        final LocalDate nuevaFecha = campoNuevaFecha.getValue();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MM yyyy");

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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                return null;
            }
        };
        new Thread(tarea).start();
    }
}
