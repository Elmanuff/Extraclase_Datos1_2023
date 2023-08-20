package com.javafx.extraclase_datos1_2023;

import java.net.*;
import java.io.*;

public class Servidor {

    private Socket socket;
    private ServerSocket serverSocket;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public void Conexion(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("Esperando conexión entrante en el puerto " + String.valueOf(puerto) + "...");
            System.out.println("server");

            socket = serverSocket.accept();
            System.out.println("Conexión establecida con: " + socket.getInetAddress().getHostName());



        } catch (Exception e) {
            System.out.println("error al iniciar conexion");
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
            entrada.readUTF();
            if (entrada.readUTF() !=null){
                ventana2_controller.recibir_mensaje(entrada.readUTF());
            }

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

    public void ejecutar(int puerto) throws IOException {
        mainAplication.abrir_ventana_chat(true);
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                Conexion(puerto);
                try {
                    entrada = new DataInputStream(socket.getInputStream());
                    salida = new DataOutputStream(socket.getOutputStream());
                    recibir();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    cerrar_Conexion();
                }
            }
        });
        hilo.start();
    }
}


