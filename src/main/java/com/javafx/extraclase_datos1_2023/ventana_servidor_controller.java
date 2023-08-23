package com.javafx.extraclase_datos1_2023;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ventana_servidor_controller {
    @FXML
    public TextField texto_mensaje_servidor;
    @FXML
    public TextArea panel_mensajes_servidor = new TextArea();

    @FXML
    public void recibir_mensaje(String mensaje){
        panel_mensajes_servidor.appendText("Cliente: " + mensaje + "\n");
    }

    @FXML
    public void enviar_mensaje(){
        String mensaje = texto_mensaje_servidor.getText();
        if (!mensaje.isEmpty()) {
            panel_mensajes_servidor.appendText("Tu: " + mensaje + "\n");
            MainApplication.servidor.enviar(mensaje);
            texto_mensaje_servidor.clear();
        }
    }

    public void limpiar_panel(){
        panel_mensajes_servidor.clear();
    }
}
