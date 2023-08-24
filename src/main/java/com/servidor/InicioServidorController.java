package com.Servidor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class InicioServidorController {
    @FXML
    public TextField texto_puerto;
    @FXML
    public Label validacion_datos = new Label();

    @FXML
    public void conectar(){
        if (!texto_puerto.getText().isEmpty()) {
            try {
                MainServidor.servidor.ejecutar(Integer.parseInt(texto_puerto.getText()));
                validacion_datos.setText("Esperando conexion...");
                validacion_datos.setTextFill(Color.GREEN);

            } catch (Exception e) {
                validarDatos();
                throw new RuntimeException(e);
            }
        }else {
            validarDatos();
        }
    }

    @FXML
    public void validarDatos(){
        validacion_datos.setText("Puerto Invalido");
        validacion_datos.setTextFill(Color.RED);
    }
}