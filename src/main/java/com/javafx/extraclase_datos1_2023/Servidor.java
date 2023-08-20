package com.javafx.extraclase_datos1_2023;

import java.net.*;
import java.io.*;

public class Servidor {

    private static Socket socket;
    private static ServerSocket serverSocket;
    private static DataInputStream entrada;
    private static DataOutputStream salida;

    public static void Conexion(int puerto) {
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
            salida.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void recibir(){
        try {
            entrada.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cerrar_Conexion(){
        try {
            salida.close();
            entrada.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ejecutar(int puerto) throws IOException {
        mainAplication.abrir_ventana_chat();
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


