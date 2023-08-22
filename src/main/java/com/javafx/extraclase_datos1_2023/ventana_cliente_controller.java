package com.javafx.extraclase_datos1_2023;

import javafx.application.Platform;
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
            mostrar_mensaje_cliente(mensaje);
        });
    }

    public void mostrar_mensaje_cliente(String msj){
            panel_mensajes_cliente.appendText( mensaje_recibido + "\n");
    }

    @FXML
    public void enviar_mensaje(){
        panel_mensajes_cliente.appendText(texto_mensaje_cliente.getText() + "\n");
        mainAplication.cliente.enviar(texto_mensaje_cliente.getText());
    }



    public void initialize() {
        System.out.println("Initialize ventana_servidor_controller");
        if (panel_mensajes_cliente == null) {
            System.out.println("panel_mensajes es nulo en initialize de ventana_servidor_controller");
        }
    }
}
