package com.javafx.extraclase_datos1_2023;

import javafx.application.Platform;

import java.net.*;
import java.io.*;

public class Servidor {

    public Socket socket;
    public DataInputStream entrada_servidor;
    public DataOutputStream salida_servidor;

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
            try (ServerSocket serverSocket = new ServerSocket(puerto)) {
                System.out.println("Esperando conexión entrante en el puerto " + puerto + "...");

                socket = serverSocket.accept();
            }
            System.out.println("Conexión establecida con: " + socket.getInetAddress().getHostName());



        } catch (Exception e) {
            System.out.println("error al iniciar conexion");
        }
    }

    public void abrirFlujos() {
        try {
            entrada_servidor = new DataInputStream(socket.getInputStream());
            salida_servidor = new DataOutputStream(socket.getOutputStream());
            salida_servidor.flush();
        } catch (IOException e) {
            System.out.println("Error en la apertura de flujos");
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
            //noinspection InfiniteLoopStatement
            while (true)
            {
                String mensaje_recibido_servidor = entrada_servidor.readUTF();
                Platform.runLater(() -> mainAplication.ventanaServidorController.recibir_mensaje(mensaje_recibido_servidor));
                System.out.println("Cliente dice: " + mensaje_recibido_servidor);

            }
        } catch (EOFException e) {
            System.out.println("Cliente cerró la conexión.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrar_Conexion(){
        try {
            salida_servidor.close();
            entrada_servidor.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


