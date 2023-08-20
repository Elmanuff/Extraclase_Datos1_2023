package com.javafx.extraclase_datos1_2023;

import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.Scanner;

public class ventana2_controller {
    @FXML
    private TextField texto_mensaje;

    @FXML
    private TextArea panel_mensajes;

    @FXML
    private Label usuario_conversacion;

    public static boolean tipo_usuario;

    public static String mensaje_auxiliar;
    public String mensaje_auxiliar2 = mensaje_auxiliar;

    public void enviar_mensaje(){
        panel_mensajes.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        panel_mensajes.appendText(texto_mensaje.getText()+"\n");

        Servidor servidor = new Servidor();
        Cliente cliente = new Cliente();

        if (tipo_usuario){
            servidor.enviar(texto_mensaje.getText());
        }else {
            cliente.enviar(texto_mensaje.getText());
        }
    }

    public static void recibir_mensaje(String mensaje){
        mensaje_auxiliar = mensaje;

    }

    public void nombre_usuario(String a){
        usuario_conversacion.setText(a);
    }

    public void anadir_mensaje(String mensaje){
        panel_mensajes.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        panel_mensajes.appendText(mensaje_auxiliar2+"\n");
    }
}
