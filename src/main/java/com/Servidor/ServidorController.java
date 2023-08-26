package com.Servidor;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Esta clase se encarga de controllar el texto en el panel.
 */
public class ServidorController {
    @FXML
    public TextField texto_mensaje_servidor;
    @FXML
    public TextArea panel_mensajes_servidor = new TextArea();

    /**
     * Muestra el texto recibido en la ventana de chat.
     * @param mensaje es el mensaje de texto que se debe poner en la ventana.
     */
    @FXML
    public void recibirMensaje(String mensaje){
        panel_mensajes_servidor.appendText("Cliente: " + mensaje + "\n");
    }

    /**
     * Muestra el texto enviado en la ventana de chat.
     */
    @FXML
    public void enviarMensaje(){
        String mensaje = texto_mensaje_servidor.getText();
        if (!mensaje.isEmpty()) {
            panel_mensajes_servidor.appendText("Tu: " + mensaje + "\n");
            com.Servidor.MainServidor.servidor.enviar(mensaje);
            texto_mensaje_servidor.clear();
        }
    }

    /**
     * Limpia la ventana de chat
     */
    public void limpiarPanel(){
        panel_mensajes_servidor.clear();
    }
}
