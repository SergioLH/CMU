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
import main.java.cmu.Usuario;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@SuppressWarnings("ALL")
public class ListarUsuariosController {

    @FXML
    private JFXButton botonEditar;

    @FXML
    private JFXButton botonEliminar;

    @FXML
    private JFXButton botonCrearFactura;

    @FXML
    private JFXButton botonVolverMenu;

    @FXML
    TableView<Usuario> tablaListarUsuario;

    @FXML
    TableColumn<Usuario, String> columnaDNI;

    @FXML
    TableColumn<Usuario, String> columnaNombre;

    @FXML
    TableColumn<Usuario, String> columnaApellidos;

    @FXML
    TableColumn<Usuario, String> columnaTipo;

    private ObservableList<Usuario> data = FXCollections.observableArrayList(
            new Usuario("258933", "John", "Smith", "Anual"
                    , "Residente", "123456789", "C/Amapolas", ""),
            new Usuario("2258933", "Peter", "Smith", ""
                    , "Trabajador", "123456789", "C/Amapolas", "1500.22")
    );

    @FXML
    private void initialize() {
        columnaDNI.setCellValueFactory(celda -> celda.getValue().dniProperty());
        columnaNombre.setCellValueFactory(celda -> celda.getValue().nombreProperty());
        columnaApellidos.setCellValueFactory(celda -> celda.getValue().apellidosProperty());
        columnaTipo.setCellValueFactory(celda -> celda.getValue().tipoUsuarioProperty());
        tablaListarUsuario.setItems(data);
    }

    @FXML
    private void botonEditar() {

        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                editar();

                return null;
            }
        };
        tarea.setOnFailed(event -> {
            event.getSource().getException().printStackTrace();
        });
        new Thread(tarea).start();
    }

    private void editar() throws IOException {
        Usuario usuario = tablaListarUsuario.getSelectionModel().getSelectedItem();
        if (usuario != null) {

            Stage ventana = MainApp.primaryStage;
            Platform.runLater(() -> {
                Scene escena = ventana.getScene();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("Editar.fxml"));
                Usuario seleccionado = tablaListarUsuario.getSelectionModel().getSelectedItem();

                try {
                    escena.setRoot(loader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
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
    private void botonEliminar() throws IOException {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                eliminar();
                Usuario usuario = tablaListarUsuario.getSelectionModel().getSelectedItem();
                tablaListarUsuario.getItems().remove(usuario);
                return null;
            }
        };
        new Thread(tarea).start();
    }

    private void eliminar() throws IOException {
        URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/UsuarioEliminado");
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

    @FXML
    private void botonCrearFactura() {

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

