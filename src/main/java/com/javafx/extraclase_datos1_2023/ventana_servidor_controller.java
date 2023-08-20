package com.javafx.extraclase_datos1_2023;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ventana_servidor_controller {
    @FXML
    private TextField texto_mensaje;

    @FXML
    private TextArea panel_mensajes;

    @FXML
    private Label usuario_conversacion;

    @FXML
    public void enviar_mensaje(){
        panel_mensajes.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        panel_mensajes.appendText(texto_mensaje.getText() + "\n");
        mainAplication.servidor.enviar(texto_mensaje.getText());
    }

    @FXML
    public void recibir_mensaje(String mensaje){
        System.out.println("Recibiendo mensaje en ventana_servidor_controller: " + mensaje); // Depuración
        Platform.runLater(() -> {
            if (panel_mensajes == null) {
                System.out.println("panel_mensajes es nulo en ventana_servidor_controller");
            }
            panel_mensajes.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            panel_mensajes.appendText(mensaje + "\n");
        });
    }
}
