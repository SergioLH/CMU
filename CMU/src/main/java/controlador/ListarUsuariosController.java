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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.cmu.Usuario;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static main.java.controlador.Herramientas.mensajeError;
import static main.java.controlador.Herramientas.mensajeInformacion;


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

    @FXML
    TableColumn<Usuario, String> columnaTipoAlojamiento;

    private ObservableList<Usuario> data = FXCollections.observableArrayList(

    );

    public void setData(ObservableList<Usuario> data) {
        this.data = data;
        tablaListarUsuario.setItems(data);
    }

    @FXML
    private void initialize() {
        columnaDNI.setCellValueFactory(celda -> celda.getValue().dniProperty());
        columnaNombre.setCellValueFactory(celda -> celda.getValue().nombreProperty());
        columnaApellidos.setCellValueFactory(celda -> celda.getValue().apellidosProperty());
        columnaTipo.setCellValueFactory(celda -> celda.getValue().tipoUsuarioProperty());
        columnaTipoAlojamiento.setCellValueFactory(celda -> celda.getValue().tipoAlojamientoProperty());
        tablaListarUsuario.setItems(data);
        botonCrearFactura.setDisable(true);

        tablaListarUsuario.getSelectionModel().selectedItemProperty().addListener((obs, old, newSelected) -> {
            if (newSelected != null) {
                if (newSelected.getTipoUsuario().equals("Residente")) {
                    botonCrearFactura.setDisable(false);
                } else {
                    botonCrearFactura.setDisable(true);
                }
            }
        });
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
                loader.setLocation(MainApp.class.getResource("../vista/Editar.fxml"));
                Usuario seleccionado = tablaListarUsuario.getSelectionModel().getSelectedItem();
                try {
                    Pane p = loader.load();
                    EditarController controlador = loader.getController();
                    controlador.setTipo(seleccionado.getTipoUsuario());
                    escena.setRoot(p);
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
            mensajeError((String) jsonObject.get("mensaje"), "Para editar, debes seleccionar una fila.");
            connection.disconnect();
        }
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
        new Thread(tarea).start();
    }

    private void eliminar() throws IOException {
        Usuario usuario = tablaListarUsuario.getSelectionModel().getSelectedItem();
        if (usuario != null) {
            int idEliminar = usuario.getIdUsuario();
            URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/ListaUsuarios/" + idEliminar);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() == 200) {
                tablaListarUsuario.getItems().remove(usuario);
                mensajeInformacion("Usuario eliminado", "Usuario eliminado correctamente.");
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
            mensajeError((String) jsonObject.get("mensaje"), "Para eliminar, debes seleccionar una fila.");
            connection.disconnect();
        }
    }

    @FXML
    private void botonCrearFactura() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                crearFactura();
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

    private void crearFactura() throws IOException {
        Usuario usuario = tablaListarUsuario.getSelectionModel().getSelectedItem();
        if (usuario != null) {
            URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/FacturaCreada");
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
            mensajeInformacion("Factura creada", (String) jsonObject.get("mensaje"));
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

