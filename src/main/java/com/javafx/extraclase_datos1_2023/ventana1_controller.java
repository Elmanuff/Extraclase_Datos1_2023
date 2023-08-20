package com.javafx.extraclase_datos1_2023;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Scanner;


public class ventana1_controller {
    @FXML
    private TextField texto_ip;

    @FXML
    private TextField texto_puerto;

    @FXML
    protected void conectar(){
        try{
            Servidor servidor = new Servidor();
            servidor.ejecutar(Integer.parseInt(texto_puerto.getText()));

            Cliente cliente = new Cliente();
            cliente.ejecutar(texto_ip.getText(),Integer.parseInt(texto_puerto.getText()));

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}