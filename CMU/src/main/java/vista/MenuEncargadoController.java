/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.vista;

import com.jfoenix.controls.JFXButton;
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
public class MenuEncargadoController {

    @FXML
    private JFXButton botonRegistrar;

    @FXML
    private JFXButton botonListarUsuarios;

    @FXML
    private JFXButton botonListarHabitaciones;

    @FXML
    private JFXButton botonModificarFecha;

    @FXML
    private JFXButton botonConsultarHistorico;

    @FXML
    private JFXButton botonSalir;

    @FXML
    private void initialize() {
    }

    @FXML
    private void botonRegistrar() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("TipoUsuario.fxml"));
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

    @FXML
    private void botonListarUsuarios() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("ListarUsuarios.fxml"));
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

    @FXML
    private void botonListarHabitaciones() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("ListarHabitaciones.fxml"));
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

    @FXML
    private void botonModificarFecha() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("ModificarFecha.fxml"));
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

    @FXML
    private void botonConsultarHistorico() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                salir();
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("ConsultarHistorico.fxml"));
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

    @FXML
    private void botonSalir() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("Portada.fxml"));
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

    private void salir() {
    }
}

