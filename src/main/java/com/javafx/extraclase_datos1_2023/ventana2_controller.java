package com.javafx.extraclase_datos1_2023;

import javafx.fxml.FXML;
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
    private static Label usuario_conversacion;

    public void anadir_mensaje_propio(){
        panel_mensajes.appendText(texto_mensaje.getText()+"\n");
    }



    public static void nombre_usuario(String a){
        usuario_conversacion.setText(a);
    }
}
