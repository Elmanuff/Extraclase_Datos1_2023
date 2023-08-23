package com.javafx.extraclase_datos1_2023;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ventana_inicio_controller {
    @FXML
    public TextField texto_ip;
    @FXML
    public TextField texto_puerto;
    @FXML
    public Label validacion_datos = new Label();

    @FXML
    public void conectar(){
        if (!texto_puerto.getText().isEmpty() && !texto_ip.getText().isEmpty()) {
            try {
                MainApplication.servidor.ejecutar(Integer.parseInt(texto_puerto.getText()));
                MainApplication.cliente.ejecutar(texto_ip.getText(), Integer.parseInt(texto_puerto.getText()));
                validacion_datos.setText("Datos validos");

            } catch (Exception e) {
                validar_datos();
                throw new RuntimeException(e);
            }
        }else {
            validar_datos();
        }
    }

    @FXML
    public void validar_datos(){
        validacion_datos.setText("Ip o puerto invalidos");
        validacion_datos.setTextFill(Color.RED);
    }
}