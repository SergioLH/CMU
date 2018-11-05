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
import main.java.cmu.Historico;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static main.java.controlador.Herramientas.mensajeError;
import static main.java.controlador.Herramientas.mensajeInformacion;

@SuppressWarnings("ALL")
public class ConsultarHistoricoController {

    @FXML
    private JFXButton botonImprimir;

    @FXML
    private JFXButton botonVolverMenu;

    @FXML
    TableView<Historico> tablaListarHistorico;

    @FXML
    TableColumn<Historico, String> columnanumeroTarjeta;

    @FXML
    TableColumn<Historico, String> columnaLugar;

    @FXML
    TableColumn<Historico, String> columnaFecha;

    private ObservableList<Historico> data = FXCollections.observableArrayList();

    public void setData(ObservableList<Historico> data) {
        this.data = data;
        tablaListarHistorico.setItems(data);
    }

    @FXML
    private void initialize() {
        columnanumeroTarjeta.setCellValueFactory(celda -> celda.getValue().numeroTarjetaProperty());
        columnaFecha.setCellValueFactory(celda -> celda.getValue().fechaProperty());
        columnaLugar.setCellValueFactory(celda -> celda.getValue().lugarProperty());
        tablaListarHistorico.setItems(data);
    }

    @FXML
    private void botonImprimir() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                imprimir();
                return null;
            }
        };
        tarea.setOnFailed(event -> {
            event.getSource().getException().printStackTrace();
        });
        tarea.setOnFailed(event -> {
            event.getSource().getException().printStackTrace();
        });
        new Thread(tarea).start();
    }

    private void imprimir() throws IOException {
        Historico historico = tablaListarHistorico.getSelectionModel().getSelectedItem();
        if (historico != null) {
            URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/FacturaImpresa");
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
            mensajeInformacion("Fichero impreso", (String) jsonObject.get("mensaje"));
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
            mensajeError((String) jsonObject.get("mensaje") + " en la impresion",
                    "Debes seleccionar un fichero para poder imprimirlo");
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
