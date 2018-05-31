/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controlador;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.cmu.Habitacion;
import main.java.cmu.Historico;
import main.java.cmu.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

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
                    loader.setLocation(MainApp.class.getResource("../vista/TipoUsuario.fxml"));
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

    @FXML
    private void botonListarUsuarios() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                ObservableList<Usuario> usuarios = listarUsuarios();
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("../vista/ListarUsuarios.fxml"));
                    try {
                        Pane p = loader.load();
                        ListarUsuariosController controlador = loader.getController();
                        controlador.setData(usuarios);
                        escena.setRoot(p);
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

    private ObservableList<Usuario> listarUsuarios() throws IOException {
        URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/ListaUsuarios");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Error: HTTP codigo error: " + connection.getResponseCode());
        }
        JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(connection.getInputStream()));
        JSONObject jsonObject = new JSONObject(jsonTokener);
        JSONArray listaUsuarios = jsonObject.getJSONArray("usuarios");
        Iterator<Object> iterator = listaUsuarios.iterator();
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            JSONObject factObj = (JSONObject) iterator.next();
            int id = Integer.parseInt((String) factObj.get("id"));
            String dni = factObj.get("dni") + "";
            String nombre = (String) factObj.get("nombre");
            String apellidos = (String) factObj.get("apellidos");
            String tipoUsuario = (String) factObj.get("TipoUsuario");
            String tipoAlojamiento = (String) factObj.get("TipoAlojamiento");
            String telefono = (String) factObj.get("telefono");
            String direccion = (String) factObj.get("direccion");
            String sueldo = factObj.get("sueldo") + "";
            int randomTipo = ThreadLocalRandom.current().nextInt(1, 4);
            switch (randomTipo) {
                case 1:
                    tipoUsuario = "Residente";
                    int randomAlojamiento = ThreadLocalRandom.current().nextInt(1, 4);
                    switch (randomAlojamiento) {
                        case 1:
                            tipoAlojamiento = "Anual";
                            break;
                        case 2:
                            tipoAlojamiento = "Mensual";
                            break;
                        case 3:
                            tipoAlojamiento = "Diario";
                            break;
                    }
                    break;
                case 2:
                    tipoUsuario = "Invitado";
                    tipoAlojamiento = "-";
                    break;
                case 3:
                    tipoUsuario = "Trabajador";
                    tipoAlojamiento = "-";
                    break;
            }
            usuarios.add(new Usuario(id, dni, nombre, apellidos, tipoAlojamiento, tipoUsuario, telefono, direccion, sueldo));
        }
        connection.disconnect();
        return usuarios;
    }

    @FXML
    private void botonListarHabitaciones() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                ObservableList<Habitacion> habitaciones = listarHabitaciones();
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("../vista/ListarHabitaciones.fxml"));
                    try {
                        Pane p = loader.load();
                        ListarHabitacionesController controlador = loader.getController();
                        controlador.setData(habitaciones);
                        escena.setRoot(p);
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

    private ObservableList<Habitacion> listarHabitaciones() throws IOException {
        URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/ListaHabitaciones");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Error: HTTP codigo error: " + connection.getResponseCode());
        }
        JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(connection.getInputStream()));
        JSONObject jsonObject = new JSONObject(jsonTokener);
        JSONArray listaHabitaciones = jsonObject.getJSONArray("habitaciones");

        Iterator<Object> iterator = listaHabitaciones.iterator();
        ObservableList<Habitacion> habitaciones = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            JSONObject factObj = (JSONObject) iterator.next();
            int id = Integer.parseInt((String) factObj.get("id"));
            String numeroTarjeta = factObj.get("numeroTarjeta") + "";
            String nombre = (String) factObj.get("nombre");
            String apellidos = (String) factObj.get("apellidos");
            String numero = factObj.get("numero") + "";
            String piso = factObj.get("piso") + "";
            habitaciones.add(new Habitacion(id, numeroTarjeta, nombre, apellidos, numero, piso));
        }
        connection.disconnect();
        return habitaciones;
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
                    loader.setLocation(MainApp.class.getResource("../vista/ModificarFecha.fxml"));
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

    @FXML
    private void botonConsultarHistorico() {
        Task<Void> tarea = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                ObservableList<Historico> historicos = listarHistoricos();
                Stage ventana = MainApp.primaryStage;
                Platform.runLater(() -> {
                    Scene escena = ventana.getScene();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("../vista/ConsultarHistorico.fxml"));
                    try {
                        Pane p = loader.load();
                        ConsultarHistoricoController controlador = loader.getController();
                        controlador.setData(historicos);
                        escena.setRoot(p);
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

    private ObservableList<Historico> listarHistoricos() throws IOException, ParseException {
        URL url = new URL("http://5b04451e0f8d4c001440b0df.mockapi.io/ListaHistoricos");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Error: HTTP codigo error: " + connection.getResponseCode());
        }
        JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(connection.getInputStream()));
        JSONObject jsonObject = new JSONObject(jsonTokener);
        JSONArray listaHistoricos = jsonObject.getJSONArray("historicos");

        Iterator<Object> iterator = listaHistoricos.iterator();
        ObservableList<Historico> historicos = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            JSONObject factObj = (JSONObject) iterator.next();
            String numeroTarjeta = factObj.get("numeroTarjeta") + "";
            String lugar = (String) factObj.get("lugar");
            String fecha = (String) factObj.get("fecha");

            Date fechaAux = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(fecha);
            String fechaFormateada = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(fechaAux);

            int randomLugar = ThreadLocalRandom.current().nextInt(1, 4);
            switch (randomLugar) {
                case 1:
                    lugar = "Gimnasio";
                    break;
                case 2:
                    lugar = "Sala comun";
                    break;
                case 3:
                    lugar = "Biblioteca";
                    break;
            }

            historicos.add(new Historico(numeroTarjeta, lugar, fechaFormateada));
        }
        connection.disconnect();
        return historicos;
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
                    loader.setLocation(MainApp.class.getResource("../vista/Portada.fxml"));
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

