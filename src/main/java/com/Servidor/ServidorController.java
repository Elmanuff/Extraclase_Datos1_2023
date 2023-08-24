package com.Servidor;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServidorController {
    @FXML
    public TextField texto_mensaje_servidor;
    @FXML
    public TextArea panel_mensajes_servidor = new TextArea();

    @FXML
    public void recibirMensaje(String mensaje){
        panel_mensajes_servidor.appendText("Cliente: " + mensaje + "\n");
    }

    @FXML
    public void enviarMensaje(){
        String mensaje = texto_mensaje_servidor.getText();
        if (!mensaje.isEmpty()) {
            panel_mensajes_servidor.appendText("Tu: " + mensaje + "\n");
            com.Servidor.MainServidor.servidor.enviar(mensaje);
            texto_mensaje_servidor.clear();
        }
    }

    public void limpiarPanel(){
        panel_mensajes_servidor.clear();
    }
}