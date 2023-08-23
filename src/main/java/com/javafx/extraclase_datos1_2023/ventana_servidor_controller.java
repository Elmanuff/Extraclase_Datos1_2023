package com.javafx.extraclase_datos1_2023;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ventana_servidor_controller {
    @FXML
    public TextField texto_mensaje_servidor;

    @FXML
    public TextArea panel_mensajes_servidor = new TextArea();

    private String mensaje_recibido;

    @FXML
    public void recibir_mensaje(String mensaje){;
        System.out.println("este es el mensaje que llego al servidor" + mensaje);
        this.mensaje_recibido = mensaje;
        System.out.println(this.mensaje_recibido);
    }

    public void mostrar_mensaje_servidor(){
        System.out.println(this.mensaje_recibido + "1");
        panel_mensajes_servidor.appendText(this.mensaje_recibido + "\n");
        panel_mensajes_servidor.applyCss();
        panel_mensajes_servidor.layout();

    }

    @FXML
    public void enviar_mensaje(){
        mostrar_mensaje_servidor();
        panel_mensajes_servidor.appendText(texto_mensaje_servidor.getText() + "\n");
        mainAplication.servidor.enviar((texto_mensaje_servidor.getText()));
        texto_mensaje_servidor.clear();
    }

    public void initialize() {
        System.out.println("Initialize ventana_servidor_controller");
        if (panel_mensajes_servidor == null) {
            System.out.println("panel_mensajes es nulo en initialize de ventana_servidor_controller");
        }
    }
}
