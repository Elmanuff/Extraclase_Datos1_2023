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
    private Label usuario_conversacion;

    public void anadir_mensaje(String mensaje){
        panel_mensajes.appendText(mensaje);
    }
}
