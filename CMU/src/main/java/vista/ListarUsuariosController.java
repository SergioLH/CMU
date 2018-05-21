package main.java.vista;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


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

    /*
        @FXML
        TableView<Lista> tablaListar;

        @FXML
        TableColumn<Lista, String> columnaDNI;

        @FXML
        TableColumn<Lista, String> columnaNombre;

        @FXML
        TableColumn<Lista, String> columnaApellidos;

        @FXML
        TableColumn<Lista, String> columnaTipo;
    */
    @FXML
    private void initialize() {/*
        columnaDNI.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDNI()));
        columnaNombre.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNombre()));
        columnaApellidos.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getApellidos()));
        columnaTipo.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTipo()));
        actualizarLista();*/
    }

    /*
        @FXML
        private void botonEditar() {
            String tipo;
            if (tipo = Invitado) {
                Task<Void> tarea = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Stage ventana = MainApp.primaryStage;
                        Platform.runLater(() -> {
                            Scene escena = ventana.getScene();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(MainApp.class.getResource("EditarInvitado.fxml"));
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
            } else if (tipo = Residente) {
                Task<Void> tarea = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Stage ventana = MainApp.primaryStage;
                        Platform.runLater(() -> {
                            Scene escena = ventana.getScene();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(MainApp.class.getResource("EditarResidente.fxml"));
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
            } else {
                Task<Void> tarea = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Stage ventana = MainApp.primaryStage;
                        Platform.runLater(() -> {
                            Scene escena = ventana.getScene();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(MainApp.class.getResource("EditarTrabajador.fxml"));
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
        }

        @FXML
        private void botonEliminar() {
            try {
                Lista lista = this.tablaListar.getSelectionModel().getSelectedItem();
                Task<Void> tarea = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        eliminarUsuario();
                        actualizarLista();
                        return null;
                    }
                };
                new Thread(tarea).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void actualizarLista() {
            Task<Void> tarea = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    tablaListar.getItems().clear();
                    tablaListar.setItems(FXCollections.observableArrayList();
                    return null;
                }
            };
            new Thread(tarea).start();
        }

        @FXML
        private void botonCrearFactura() {

        }
    */
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
        new Thread(tarea).start();
    }
}
