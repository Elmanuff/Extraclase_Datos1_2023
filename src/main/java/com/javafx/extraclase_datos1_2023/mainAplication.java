package com.javafx.extraclase_datos1_2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class mainAplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainAplication.class.getResource("ventana1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Aplicacion de Mensajeria");
        stage.setScene(scene);
        stage.show();
    }

    public static void abrir_ventana_chat() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainAplication.class.getResource("ventana2.fxml"));
        Scene secondaryScene = new Scene(fxmlLoader.load(), 600, 400);

        Stage ventana_chat = new Stage();
        ventana_chat.setTitle("Chat");
        ventana_chat.setScene(secondaryScene);
        ventana_chat.show();
    }

    public static void main(String[] args) {
        launch();
    }
}