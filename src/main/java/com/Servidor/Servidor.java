package com.Servidor;

import javafx.application.Platform;
import java.net.*;
import java.io.*;

public class Servidor {
    public Socket socket;
    public DataInputStream entrada_servidor;
    public DataOutputStream salida_servidor;

    public void ejecutar(int puerto){
        Thread hilo = new Thread(() -> {
            try {
                conexion(puerto);
                Platform.runLater(() -> {
                    try {
                        com.Servidor.MainServidor.abrirVentanaChat();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                abrirFlujos();
                recibir();
            } finally {
                cerrarConexion();
                Platform.exit();
            }
        });
        hilo.start();
    }

    public void conexion(int puerto) {
        try {
            try (ServerSocket serverSocket = new ServerSocket(puerto)) {
                System.out.println("Esperando conexión en el puerto " + puerto + "...");
                socket = serverSocket.accept();
                System.out.println("Conexión establecida con: " + socket.getInetAddress().getHostName());
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar conexion");
        }
    }

    public void abrirFlujos() {
        try {
            entrada_servidor = new DataInputStream(socket.getInputStream());
            salida_servidor = new DataOutputStream(socket.getOutputStream());
            salida_servidor.flush();
        } catch (IOException e) {
            System.out.println("Error al abrir flujos");
        }
    }

    public void enviar(String mensaje){
        try {
            salida_servidor.writeUTF(mensaje);
            salida_servidor.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void recibir(){
        try {
            while (true)
            {
                String mensaje_recibido_servidor = entrada_servidor.readUTF();
                Platform.runLater(() -> com.Servidor.MainServidor.ventanaServidorController.recibirMensaje(mensaje_recibido_servidor));
                System.out.println("Cliente dice: " + mensaje_recibido_servidor);

            }
        } catch (EOFException e) {
            System.out.println("Conexión cerrada por el cliente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrarConexion(){
        try {
            salida_servidor.close();
            entrada_servidor.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}