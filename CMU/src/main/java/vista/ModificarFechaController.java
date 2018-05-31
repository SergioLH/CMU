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
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

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
                if (confirmar() == true) {
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
                }
                return null;
            }
        };
        tarea.setOnFailed(event -> {
            event.getSource().getException().printStackTrace();
        });
        new Thread(tarea).start();
    }

    private boolean confirmar() throws IOException {
        final String numeroTarjeta = campoTarjeta.getText();
        final LocalDate nuevaFecha = campoNuevaFecha.getValue();

        if (!(campoTarjeta.getText().isEmpty()) && !(campoNuevaFecha.getValue().isBefore(LocalDate.now()))) {
            URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/FechaModificada");
            //URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io//tarjetas");
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
        tarea.setOnFailed(event -> {
            event.getSource().getException().printStackTrace();
        });
        new Thread(tarea).start();
    }
}
