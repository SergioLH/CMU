/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
                if (logearse()) {
                    Stage ventana = MainApp.primaryStage;
                    Platform.runLater(() -> {
                        Scene escena = ventana.getScene();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(MainApp.class.getResource("../vista/MenuEncargado.fxml"));
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

    private boolean logearse() throws IOException {
        final String id = campoID.getText();
        final String contraseña = campoContraseña.getText();

        if (!(campoID.getText().isEmpty()) && !(campoContraseña.getText().isEmpty())) {
            URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/login");
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
            System.out.println("Error");
            return false;
        }
    }
}
