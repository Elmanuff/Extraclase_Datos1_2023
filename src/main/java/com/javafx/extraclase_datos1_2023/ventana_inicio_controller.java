package com.javafx.extraclase_datos1_2023;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ventana_inicio_controller {
    @FXML
    public TextField texto_ip;
    @FXML
    public TextField texto_puerto;

    @FXML
    public void conectar(){
        try{
            MainApplication.servidor.ejecutar(Integer.parseInt(texto_puerto.getText()));

            MainApplication.cliente.ejecutar(texto_ip.getText(),Integer.parseInt(texto_puerto.getText()));

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}