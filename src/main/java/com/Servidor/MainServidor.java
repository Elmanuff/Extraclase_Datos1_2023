package com.Servidor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainServidor extends Application {
    public static Servidor servidor = new Servidor();
    static ServidorController ventanaServidorController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainServidor.class.getResource("VentanaInicioServidor.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Mensajeria (Servidor)");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
    }

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

    public static void main(String[] args) {
        launch();
    }
}