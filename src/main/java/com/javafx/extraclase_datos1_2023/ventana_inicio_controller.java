package com.javafx.extraclase_datos1_2023;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ventana_inicio_controller {

    @FXML
    private TextField texto_ip;

    @FXML
    private TextField texto_puerto;

    @FXML
    public void conectar(){

        try{
            mainAplication.servidor.ejecutar(Integer.parseInt(texto_puerto.getText()));

            mainAplication.cliente.ejecutar(texto_ip.getText(),Integer.parseInt(texto_puerto.getText()));

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}