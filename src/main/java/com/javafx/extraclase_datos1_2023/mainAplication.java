package com.javafx.extraclase_datos1_2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class mainAplication extends Application {
    public static Servidor servidor = new Servidor();
    public static Cliente cliente = new Cliente();
    public static ventana2_controller ventana2Controller = new ventana2_controller();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainAplication.class.getResource("ventana1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Aplicacion de Mensajeria");
        stage.setScene(scene);
        stage.show();
    }

    public static void abrir_ventana_chat(boolean tipo) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainAplication.class.getResource("ventana2.fxml"));
        Scene secondaryScene = new Scene(fxmlLoader.load(), 600, 400);

        Stage ventana_chat = new Stage();

        if (tipo){
            ventana_chat.setTitle("Servidor");
        } else {
            ventana_chat.setTitle("Cliente");
        }

        ventana_chat.setScene(secondaryScene);
        ventana_chat.show();
    }

    public static void main(String[] args) {
        launch();
    }
}