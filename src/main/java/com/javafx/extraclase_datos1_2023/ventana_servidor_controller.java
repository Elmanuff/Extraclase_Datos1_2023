package com.javafx.extraclase_datos1_2023;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ventana_servidor_controller {
    @FXML
    public TextField texto_mensaje_servidor;

    @FXML
    public TextArea panel_mensajes_servidor = new TextArea();

    public String mensaje_recibido;

    @FXML
    public void recibir_mensaje(String mensaje){
        Platform.runLater(() -> {
            mensaje_recibido = mensaje;
            mostrar_mensaje_servidor(mensaje);
        });
    }
    public void mostrar_mensaje_servidor(String mensaje_recibido){
        panel_mensajes_servidor.appendText(mensaje_recibido + "\n");
    }

    @FXML
    public void enviar_mensaje(){
        panel_mensajes_servidor.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        panel_mensajes_servidor.appendText(texto_mensaje_servidor.getText() + "\n");
        mainAplication.servidor.enviar(texto_mensaje_servidor.getText());
    }


    public void initialize() {
        // Este método se ejecuta después de cargar el FXML
        System.out.println("Initialize ventana_servidor_controller");
        if (panel_mensajes_servidor == null) {
            System.out.println("panel_mensajes es nulo en initialize de ventana_servidor_controller");
        }
    }
}
