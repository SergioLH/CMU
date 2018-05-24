package main.java.vista;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.java.cmu.Habitacion;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("ALL")
public class ListarHabitacionesController {

    @FXML
    private JFXButton botonEliminar;

    @FXML
    private JFXButton botonVolverMenu;

    @FXML
    TableView<Habitacion> tablaListarHabitacion;

    @FXML
    TableColumn<Habitacion, String> columnaDNI;

    @FXML
    TableColumn<Habitacion, String> columnaNombre;

    @FXML
    TableColumn<Habitacion, String> columnaApellidos;

    @FXML
    TableColumn<Habitacion, String> columnaNumero;

    @FXML
    TableColumn<Habitacion, String> columnaPiso;

    private final ObservableList<Habitacion> data = FXCollections.observableArrayList(
            new Habitacion("123456", "John", "Smith", "2", "29"),
            new Habitacion("213352", "David", "Zayas", "3", "29")
    );

    @FXML
    private void initialize() {
        columnaDNI.setCellValueFactory(celda -> celda.getValue().dniProperty());
        columnaNombre.setCellValueFactory(celda -> celda.getValue().nombreProperty());
        columnaApellidos.setCellValueFactory(celda -> celda.getValue().apellidosProperty());
        columnaPiso.setCellValueFactory(celda -> celda.getValue().pisoProperty());
        columnaNumero.setCellValueFactory(celda -> celda.getValue().numeroProperty());
        tablaListarHabitacion.setItems(data);
    }

    @FXML
    private void botonEliminar() throws IOException {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                eliminar();
                return null;
            }
        };
        tarea.setOnFailed(event -> {
            event.getSource().getException().printStackTrace();
        });
        new Thread(tarea).start();
    }

    private void eliminar() throws IOException {
        Habitacion habitacion = tablaListarHabitacion.getSelectionModel().getSelectedItem();
        if (habitacion != null) {
            tablaListarHabitacion.getItems().remove(habitacion);
            URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/HabitacionEliminada");
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
