package com.javafx.extraclase_datos1_2023;

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

    public void ejecutar(String ip, int puerto) throws IOException {
        Thread hilo = new Thread(() -> {
            try {
                Conexion(ip, puerto);
                abrirFlujos();
                recibir();
            } finally {
                cerrar_Conexion();
                Platform.exit();
            }
        });
        hilo.start();
        MainApplication.abrir_ventana_chat(false);
    }

    public void Conexion(String ip, int puerto) {
        try {
            socket = new Socket(ip, puerto);
            System.out.println("Conectado a :" + socket.getInetAddress().getHostName());

        } catch (IOException e) {
            System.out.println("Error al iniciar conexion: " + e.getMessage());
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
                Platform.runLater(() -> MainApplication.ventanaClienteController.recibir_mensaje(mensaje_recibido_cliente));
                System.out.println("Servidor dice: " + mensaje_recibido_cliente);
            }
        } catch (EOFException e) {
            System.out.println("Conexión cerrada por el servidor");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void cerrar_Conexion(){
        try {
            entrada_cliente.close();
            salida_cliente.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}