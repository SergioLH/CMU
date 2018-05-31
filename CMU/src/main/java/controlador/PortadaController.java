/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controlador;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Seryak
 */
public class PortadaController {

    @FXML
    private JFXButton botonComenzar;

    @FXML
    private void initiailze() {

    }

    @FXML
    private void botonComenzar() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("../vista/Login.fxml"));

                    try {
                        escena.setRoot(loader.load());
                    } catch (Exception e) {
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

