package com.javafx.extraclase_datos1_2023;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ventana_cliente_controller {
    @FXML
    public TextField texto_mensaje_cliente;
    @FXML
    public TextArea panel_mensajes_cliente = new TextArea();

    @FXML
    public void recibir_mensaje(String mensaje){
        panel_mensajes_cliente.appendText("Servidor: " + mensaje + "\n");
    }

    @FXML
    public void enviar_mensaje(){
        String mensaje = texto_mensaje_cliente.getText();
        if (!mensaje.isEmpty()) {
            panel_mensajes_cliente.appendText("Tu: " + mensaje + "\n");
            MainApplication.cliente.enviar(mensaje);
            texto_mensaje_cliente.clear();
        }
    }

    public void limpiar_panel(){
        panel_mensajes_cliente.clear();
    }
}
