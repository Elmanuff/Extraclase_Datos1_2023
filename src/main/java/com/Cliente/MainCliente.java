package com.Cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Esta clase controla las inicializaciones para que la ventana cliente pueda funcionar.
 */
public class MainCliente extends Application {
    public static com.Cliente.Cliente cliente = new com.Cliente.Cliente();
    static com.Cliente.ClienteController ventanaClienteController;
    static com.Cliente.InicioClienteController ventanaInicioCliente;

    /**
     * Esta funcion carga el archivo FXML de la ventana de chat del cliente, crea la scena, el titulo y todo lo necesario para la interfaz grafica.
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * Applications may create other stages, if needed, but they will not be primary stages.
     * @throws IOException Esto permite que el programa mande el error si es que falla
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainCliente.class.getResource("VentanaInicioCliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        ventanaInicioCliente = fxmlLoader.getController();
        stage.setTitle("Mensajeria (Cliente)");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.show();
    }

    /**
     *  Esta funcion carga la ventana de chat y el controlador
     * @throws IOException Esto permite que el programa mande el error si es que falla
     */
    public static void abrirVentanaChat() throws IOException {
        FXMLLoader fxmlLoader2 = new FXMLLoader(MainCliente.class.getResource("ChatCliente.fxml"));
        Scene clienteScene = new Scene(fxmlLoader2.load(), 600, 400);
        Stage ventana_chat = new Stage();
        ventana_chat.resizableProperty().setValue(false);
        ventanaClienteController = fxmlLoader2.getController();

        ventana_chat.setTitle("Cliente");
        ventana_chat.setScene(clienteScene);
        ventana_chat.show();

    }

    /**
     * Esta funcion nada mas carga
     * @param args son argumentos internos necesarios para le correcto funcionamiento.
     */
    public static void main(String[] args) {
        launch();
    }
}