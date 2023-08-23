package com.javafx.extraclase_datos1_2023;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ventana_cliente_controller {
    @FXML
    public TextField texto_mensaje_cliente;

    @FXML
    public TextArea panel_mensajes_cliente = new TextArea();

    public String mensaje_recibido;


    @FXML
    public void recibir_mensaje(String mensaje){
        Platform.runLater(() -> {
            mensaje_recibido = mensaje;
            mostrar_mensaje_cliente(mensaje_recibido);
        });
    }

    public void mostrar_mensaje_cliente(String msj){
        Platform.runLater(() -> {
            panel_mensajes_cliente.appendText(msj + "\n");
            panel_mensajes_cliente.setScrollTop(Double.MAX_VALUE);
            panel_mensajes_cliente.applyCss();
            panel_mensajes_cliente.layout();
        });
    }

    @FXML
    public void enviar_mensaje(){
        panel_mensajes_cliente.appendText(texto_mensaje_cliente.getText() + "\n");
        mainAplication.cliente.enviar(texto_mensaje_cliente.getText());
        texto_mensaje_cliente.clear();
    }

    public void initialize() {
        System.out.println("Initialize ventana_servidor_controller");
        if (panel_mensajes_cliente == null) {
            System.out.println("panel_mensajes es nulo en initialize de ventana_servidor_controller");
        }
    }
}
