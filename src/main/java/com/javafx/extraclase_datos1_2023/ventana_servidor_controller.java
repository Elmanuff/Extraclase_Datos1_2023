package com.javafx.extraclase_datos1_2023;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ventana_servidor_controller {
    @FXML
    public TextField texto_mensaje_servidor;

    @FXML
    public TextArea panel_mensajes_servidor = new TextArea();

    @FXML
    public Label usuario_conversacion;

    @FXML
    public void enviar_mensaje(){
        panel_mensajes_servidor.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        panel_mensajes_servidor.appendText(texto_mensaje_servidor.getText() + "\n");
        mainAplication.servidor.enviar(texto_mensaje_servidor.getText());
    }

    @FXML
    public void recibir_mensaje(String mensaje){
        System.out.println("Recibiendo mensaje en ventana_servidor_controller: " + mensaje); // Depuración
        panel_mensajes_servidor.appendText("");
        Platform.runLater(() -> {
            if (panel_mensajes_servidor == null) {
                System.out.println("panel_mensajes es nulo en ventana_servidor_controller");
            }
            panel_mensajes_servidor.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            panel_mensajes_servidor.appendText(mensaje + "\n");
        });
    }
}
