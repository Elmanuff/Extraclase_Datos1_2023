package com.Cliente;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * Esta clase controla la ventana de inicio del cliente.
 */
public class InicioClienteController {
    @FXML
    public TextField texto_ip;
    @FXML
    public TextField texto_puerto;
    @FXML
    public Label validacion_datos = new Label();

    /**
     * Esta funcion permite validar si los puertos son iguales y conectar las ventanas del cliente y el servidor.
     */
    @FXML
    public void conectar(){
        if (!texto_puerto.getText().isEmpty() && !texto_ip.getText().isEmpty()) {
            try {
                MainCliente.cliente.ejecutar(texto_ip.getText(), Integer.parseInt(texto_puerto.getText()));
                validacion_datos.setText("");
                validacion_datos.setTextFill(Color.WHITE);

            } catch (Exception e) {
                validarDatos();
                throw new RuntimeException(e);
            }
        }else {
            validarDatos();
        }
    }

    /**
     * Muestra el mensaje de cuando los datos son invalidos
     */
    @FXML
    public void validarDatos(){
        validacion_datos.setText("Ip o puerto invalidos");
        validacion_datos.setTextFill(Color.RED);
    }
}