package com.Cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainCliente extends Application {
    public static com.Cliente.Cliente cliente = new com.Cliente.Cliente();
    static com.Cliente.ClienteController ventanaClienteController;
    static com.Cliente.InicioClienteController ventanaInicioCliente;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainCliente.class.getResource("VentanaInicioCliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        ventanaInicioCliente = fxmlLoader.getController();
        stage.setTitle("Mensajeria (Cliente)");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
    } //Esta funcion carga el archivo FXML de la ventana de chat del cliente, crea la scena, el titulo y todo lo necesario para la interfaz grafica.

    public static void abrirVentanaChat() throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(MainCliente.class.getResource("ChatCliente.fxml"));
        Scene clienteScene = new Scene(fxmlLoader2.load(), 600, 400);
        Stage ventana_chat = new Stage();
        ventana_chat.resizableProperty().setValue(false);
        ventanaClienteController = fxmlLoader2.getController();

        ventana_chat.setTitle("Cliente");
        ventana_chat.setScene(clienteScene);
        ventana_chat.show();

    } // Esta funcion carga la ventana de chat y el controlador

    public static void main(String[] args) {
        launch();
    } // Esta funcion nada mas carga
}