/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Seryak
 */
public class MainApp extends Application {

    public static Stage primaryStage;
    private static String Titulo = "CMU Pablo Serrano";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(Titulo);
        showPortada();
        this.primaryStage.show();
    }

    private void showPortada() {
        Stage ventana = MainApp.primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("../vista/Portada.fxml"));
        Scene escena = null;
        try {
            escena = new Scene(loader.load());
            escena.getStylesheets().add(getClass().getResource("../../resources/css/estilo.css").toExternalForm());
            // escena.getStylesheets().add(getClass().getResource("../../resources/css/estilo.css").toString());
            MainApp.primaryStage.setScene(escena);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
