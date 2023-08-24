package com.Cliente;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClienteController {
    @FXML
    public TextField texto_mensaje_cliente;
    @FXML
    public TextArea panel_mensajes_cliente = new TextArea();

    @FXML
    public void recibirMensaje(String mensaje){
        panel_mensajes_cliente.appendText("Servidor: " + mensaje + "\n");
    }

    @FXML
    public void enviarMensaje(){
        String mensaje = texto_mensaje_cliente.getText();
        if (!mensaje.isEmpty()) {
            panel_mensajes_cliente.appendText("Tu: " + mensaje + "\n");
            MainCliente.cliente.enviar(mensaje);
            texto_mensaje_cliente.clear();
        }
    }

    public void limpiarPanel(){
        panel_mensajes_cliente.clear();
    }
}