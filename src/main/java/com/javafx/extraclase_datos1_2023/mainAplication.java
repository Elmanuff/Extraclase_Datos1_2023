package com.javafx.extraclase_datos1_2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class mainAplication extends Application {
    public static Servidor servidor = new Servidor();
    public static Cliente cliente = new Cliente();
    public static ventana_servidor_controller ventanaServidorController = new ventana_servidor_controller();
    public static ventana_cliente_controller ventanaClienteController = new ventana_cliente_controller();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainAplication.class.getResource("ventana_inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Aplicacion de Mensajeria");
        stage.setScene(scene);
        stage.show();
    }

    public static void abrir_ventana_chat(boolean tipo) throws IOException {
        if (tipo){
            FXMLLoader fxmlLoader1 = new FXMLLoader(mainAplication.class.getResource("ventana_servidor.fxml"));
            Scene secondaryScene = new Scene(fxmlLoader1.load(), 600, 400);
            Stage ventana_chat = new Stage();
            ventanaServidorController.initialize();

            ventana_chat.setTitle("Servidor");
            ventana_chat.setScene(secondaryScene);
            ventana_chat.show();

        } else {
            FXMLLoader fxmlLoader2 = new FXMLLoader(mainAplication.class.getResource("ventana_cliente.fxml"));
            Scene secondaryScene = new Scene(fxmlLoader2.load(), 600, 400);
            Stage ventana_chat = new Stage();
            ventanaClienteController.initialize();

            ventana_chat.setTitle("Cliente");
            ventana_chat.setScene(secondaryScene);
            ventana_chat.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}