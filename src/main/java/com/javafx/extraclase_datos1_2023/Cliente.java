package com.javafx.extraclase_datos1_2023;

import java.net.*;
import java.io.*;

public class Cliente {
    private static Socket socket;
    private static DataInputStream entrada;
    private static DataOutputStream salida;

    public static void Conexion(String ip, int puerto) {
        try {
            socket = new Socket(ip, puerto);
            System.out.println("Conectado a :" + socket.getInetAddress().getHostName());
            System.out.println("cliente");

            //ventana2_controller.nombre_usuario(String.valueOf(socket.getLocalAddress().getHostName()));

        } catch (Exception e) {
            System.out.println("Excepción al levantar conexión: " + e.getMessage());
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

    public static void ejecutar(String ip, int puerto) throws IOException {
        mainAplication.abrir_ventana_chat();
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                Conexion(ip, puerto);
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
