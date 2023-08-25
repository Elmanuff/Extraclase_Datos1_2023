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
    } // Esta funcion solo sirve para mostrar el mensaje en pantalla
    @FXML
    public void mostrarMensaje(){
        String mensaje = texto_mensaje_cliente.getText();
        if (!mensaje.isEmpty()) {
            panel_mensajes_cliente.appendText("Tu: " + mensaje + "\n");
            MainCliente.cliente.enviar(mensaje);
            texto_mensaje_cliente.clear();
        }
    } //Esta funcion permite obtener el mensaje que se escribe y mostrarlo en pantalla
    public void limpiarPanel(){
        panel_mensajes_cliente.clear();
    } //Permite limpiar el panel de mensajes para mostrar nuevos
}
