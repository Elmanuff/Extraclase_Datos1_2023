package com.Cliente;

import javafx.application.Platform;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public Socket socket;
    public DataInputStream entrada_cliente;
    public DataOutputStream salida_cliente;


    public void ejecutar(String ip, int puerto){
        Thread hilo = new Thread(() -> {
            try {
                if(conexion(ip, puerto)) {
                    Platform.runLater(() -> {
                        try {
                            MainCliente.abrirVentanaChat();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                abrirFlujos();
                recibir();

            } finally {
                cerrarConexion();
                Platform.exit();
            }
        });
        hilo.start();
    } //Esta funcion sirve para ejecutar la parte del socket del cliente

    public boolean conexion(String ip, int puerto) {
        try {
            socket = new Socket(ip, puerto);
            System.out.println("Conectado a :" + socket.getInetAddress().getHostName());
            return true;

        } catch (IOException e) {
            System.out.println("Error al iniciar conexion");
            Platform.runLater(() ->MainCliente.ventanaInicioCliente.validarDatos());
            return false;
        }
    } //Esta funcion sirve para validar los datos y avisar si se esta dando la conexion al socket

    public void abrirFlujos() {
        try {
            entrada_cliente = new DataInputStream(socket.getInputStream());
            salida_cliente = new DataOutputStream(socket.getOutputStream());
            salida_cliente.flush();
        } catch (IOException e) {
            System.out.println("Error al abrir flujos");
        }
    } // Esta funcion sirve para abrir los flujos de datos entre el cliente y el server, lo cual permite que se puedan enviar datos

    public void enviar(String mensaje){
        try {
            salida_cliente.writeUTF(mensaje);
            salida_cliente.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } // Esta funcion permite que se envien datos, o mas especificamente, el mensaje que se quiere enviar del cliente al servidor

    public void recibir() {
        try {
            while (true) {
                String mensaje_recibido_cliente = entrada_cliente.readUTF();
                Platform.runLater(() -> MainCliente.ventanaClienteController.recibirMensaje(mensaje_recibido_cliente));
            }
        } catch (EOFException e) {
            System.out.println("Conexión cerrada por el servidor");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } //Esta funcion permite que el cliente este siempre pendiente a cuando le llegue un mensaje, y cuando le llegue, llamar a al controlador de la ventana para mostrarlo.

    public void cerrarConexion(){
        try {
            entrada_cliente.close();
            salida_cliente.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("No se cerro la conexion correctamente");
        }
    } // Esta funcion permite cerrar la conexion entre el cliente y el servidor
}

