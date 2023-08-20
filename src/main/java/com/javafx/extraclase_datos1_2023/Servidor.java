package com.javafx.extraclase_datos1_2023;

import javafx.application.Platform;

import java.net.*;
import java.io.*;

public class Servidor {

    public Socket socket;
    public DataInputStream entrada;
    public DataOutputStream salida;

    public void ejecutar(int puerto) throws IOException {
        mainAplication.abrir_ventana_chat(true);
        Thread hilo = new Thread(() -> {
            try {
                Conexion(puerto);
                abrirFlujos();
                recibir();
            } finally {
                cerrar_Conexion();
                Platform.exit();
            }
        });
        hilo.start();
    }

    public void Conexion(int puerto) {
        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Esperando conexión entrante en el puerto " + puerto + "...");

            socket = serverSocket.accept();
            System.out.println("Conexión establecida con: " + socket.getInetAddress().getHostName());



        } catch (Exception e) {
            System.out.println("error al iniciar conexion");
        }
    }

    public void abrirFlujos() {
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
            salida.flush();
        } catch (IOException e) {
            System.out.println("Error en la apertura de flujos");
        }
    }

    public void enviar(String mensaje){
        try {
            salida.writeUTF(mensaje);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void recibir(){
        try {
            while (true) {
                String mensaje_recibido = entrada.readUTF();
                Platform.runLater(() -> mainAplication.ventanaServidorController.recibir_mensaje(mensaje_recibido));
                System.out.println(mensaje_recibido + "servidor");
            }
        } catch (EOFException e) {
            System.out.println("Cliente cerró la conexión.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrar_Conexion(){
        try {
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


