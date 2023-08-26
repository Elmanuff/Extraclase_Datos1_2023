package com.Servidor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Esta clase es la Main del servido, la que controla
 * todo lo necesario para el correcto funcionamiento del servidor
 */
public class MainServidor extends Application {
    public static Servidor servidor = new Servidor();
    static ServidorController ventanaServidorController;

    /**
     * Esta funcion carga el archivo FXML y le configura los parametros.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException permite que al fallar, haya un error detallado con la parte y el porque del fallo.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainServidor.class.getResource("VentanaInicioServidor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Mensajeria (Servidor)");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
    }

    /**
     * Esta funcion Carga el archivo y la ventana para que posteriormente sea controlada por el Controller.
     * @throws IOException permite que al fallar, haya un error detallado con la parte y el porque del fallo.
     */
    public static void abrirVentanaChat() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(MainServidor.class.getResource("ChatServidor.fxml"));
        Scene servidorScene = new Scene(fxmlLoader1.load(), 600, 400);
        Stage ventana_chat = new Stage();
        ventana_chat.resizableProperty().setValue(false);
        ventanaServidorController = fxmlLoader1.getController();

        ventana_chat.setTitle("Servidor");
        ventana_chat.setScene(servidorScene);
        ventana_chat.show();
    }

    /**
     * Solo carga la ventana
     * @param args son argumentos necesarios para el correcto funcionamiento de la ventana.
     */
    public static void main(String[] args) {
        launch();
    } //
}