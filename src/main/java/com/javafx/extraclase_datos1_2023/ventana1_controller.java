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

    protected void conectar() throws IOException {
        mainAplication.abrir_ventana_chat();

    }

}