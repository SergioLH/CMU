package main.java.controlador;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

import static main.java.controlador.Herramientas.mensajeError;
import static main.java.controlador.Herramientas.mensajeInformacion;

@SuppressWarnings("ALL")
public class ListarHabitacionesController {

    @FXML
    private JFXButton botonEliminar;

    @FXML
    private JFXButton botonVolverMenu;

    @FXML
    TableView<Habitacion> tablaListarHabitacion;

    @FXML
    TableColumn<Habitacion, String> columnaNumeroTarjeta;

    @FXML
    TableColumn<Habitacion, String> columnaNombre;

    @FXML
    TableColumn<Habitacion, String> columnaApellidos;

    @FXML
    TableColumn<Habitacion, String> columnaNumero;

    @FXML
    TableColumn<Habitacion, String> columnaPiso;

    private ObservableList<Habitacion> data = FXCollections.observableArrayList();

    public void setData(ObservableList<Habitacion> data) {
        this.data = data;
        tablaListarHabitacion.setItems(data);
    }

    @FXML
    private void initialize() {
        columnaNumeroTarjeta.setCellValueFactory(celda -> celda.getValue().numeroTarjetaProperty());
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
                Habitacion habitacion = tablaListarHabitacion.getSelectionModel().getSelectedItem();
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
            int idEliminar = habitacion.getIdHabitacion();
            URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/ListaHabitaciones/" + idEliminar);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 200) {
                tablaListarHabitacion.getItems().remove(habitacion);
                mensajeInformacion("Habitacion eliminada", "Habitacion Eliminada Correctamente.");
            } else {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error en la conexion");
                    try {
                        alert.setHeaderText("Error: HTTP codigo error: " + connection.getResponseCode());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    alert.showAndWait();
                });
                throw new RuntimeException("Error: HTTP codigo error: " + connection.getResponseCode());
            }
            connection.disconnect();
        } else {
            URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/MensajeError");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error en la conexion");
                    try {
                        alert.setHeaderText("Error: HTTP codigo error: " + connection.getResponseCode());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    alert.showAndWait();
                });
                throw new RuntimeException("Error: HTTP codigo error: " + connection.getResponseCode());
            }
            JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(connection.getInputStream()));
            JSONObject jsonObject = new JSONObject(jsonTokener);
            mensajeError((String) jsonObject.get("mensaje"), "Para eliminar una habitacion, selecciona una fila.");
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
                    loader.setLocation(MainApp.class.getResource("../vista/MenuEncargado.fxml"));
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
