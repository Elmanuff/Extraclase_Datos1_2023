package com.javafx.extraclase_datos1_2023;

import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ventana2_controller {
    @FXML
    public TextField texto_mensaje;

    @FXML
    public TextArea panel_mensajes;

    @FXML
    public Label usuario_conversacion;

    public boolean tipo_usuario;

    public void enviar_mensaje(){
        panel_mensajes.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        panel_mensajes.appendText(texto_mensaje.getText()+"\n");

        if (tipo_usuario){
            mainAplication.servidor.enviar(texto_mensaje.getText());
        }else {
            mainAplication.cliente.enviar(texto_mensaje.getText());
        }
    }

    public void recibir_mensaje(String mensaje){
        panel_mensajes.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        panel_mensajes.appendText(mensaje+"\n");
    }
}
