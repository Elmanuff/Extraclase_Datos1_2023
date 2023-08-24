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
    }

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
    }

    public void abrirFlujos() {
        try {
            entrada_cliente = new DataInputStream(socket.getInputStream());
            salida_cliente = new DataOutputStream(socket.getOutputStream());
            salida_cliente.flush();
        } catch (IOException e) {
            System.out.println("Error al abrir flujos");
        }
    }

    public void enviar(String mensaje){
        try {
            salida_cliente.writeUTF(mensaje);
            salida_cliente.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void recibir() {
        try {
            while (true) {
                String mensaje_recibido_cliente = entrada_cliente.readUTF();
                Platform.runLater(() -> MainCliente.ventanaClienteController.recibirMensaje(mensaje_recibido_cliente));
                System.out.println("Servidor dice: " + mensaje_recibido_cliente);
            }
        } catch (EOFException e) {
            System.out.println("Conexión cerrada por el servidor");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void cerrarConexion(){
        try {
            entrada_cliente.close();
            salida_cliente.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("No se cerro la conexion correctamente");
        }
    }
}

