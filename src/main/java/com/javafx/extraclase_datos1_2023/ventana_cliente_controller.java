package com.javafx.extraclase_datos1_2023;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ventana_cliente_controller {
    @FXML
    public TextField texto_mensaje;

    @FXML
    public TextArea panel_mensajes;

    @FXML
    public Label usuario_conversacion;

    @FXML
    public void enviar_mensaje(){
        if (panel_mensajes == null) {
            System.out.println("panel_mensajes es nulo en enviar_mensaje de ventana_servidor_controller");
            return;
        }
        panel_mensajes.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        panel_mensajes.appendText(texto_mensaje.getText() + "\n");
        mainAplication.cliente.enviar(texto_mensaje.getText());
    }

    @FXML
    public void recibir_mensaje(String mensaje){
        System.out.println("Recibiendo mensaje en ventana_servidor_controller: " + mensaje);
        if (panel_mensajes == null) {
            System.out.println("panel_mensajes es nulo en recibir_mensaje de ventana_servidor_controller");
            return;
        }
        Platform.runLater(() -> {
        panel_mensajes.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        panel_mensajes.appendText(mensaje + "\n");
        });
    }

    public void initialize() {
        // Este método se ejecuta después de cargar el FXML
        System.out.println("Initialize ventana_servidor_controller");
        if (panel_mensajes == null) {
            System.out.println("panel_mensajes es nulo en initialize de ventana_servidor_controller");
        }
    }
}
