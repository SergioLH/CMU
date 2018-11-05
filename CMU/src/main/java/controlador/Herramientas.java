package main.java.controlador;

import javafx.application.Platform;
import javafx.scene.control.Alert;

@SuppressWarnings("ALL")
public class Herramientas {
    /*
     * Este metodo mostrara una alerta como aviso
     * de que se ha producido un error.
     */

    public static void mensajeError(String titulo, String mensaje) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(titulo);
            alert.setHeaderText(mensaje);
            alert.showAndWait();
        });
    }

    /*
     * Este metodo mostrara una alerta como aviso
     * de que se ha producido una accion correctamente.
     */
    public static void mensajeInformacion(String titulo, String mensaje) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(titulo);
            alert.setHeaderText(mensaje);
            alert.showAndWait();
        });
    }
}
