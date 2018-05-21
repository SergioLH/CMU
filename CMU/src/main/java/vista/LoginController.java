/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vista;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
public class LoginController {
    @FXML
    private JFXButton botonLogin;

    @FXML
    private JFXTextField campoID;

    @FXML
    private JFXPasswordField campoContraseña;

    @FXML
    private void initialize() {

    }

    @FXML
    private void botonLogin() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                logearse();
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

    private void logearse() {
        final String id = campoID.getText();
        final String contraseña = campoContraseña.getText();

        //METODO PARA LOGEARSE EN EL SERVIDOR.
    }


}
