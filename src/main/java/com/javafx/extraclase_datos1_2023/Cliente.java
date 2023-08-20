package com.javafx.extraclase_datos1_2023;

import java.net.*;
import java.io.*;

public class Cliente {
    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public void Conexion(String ip, int puerto) {
        try {
            socket = new Socket(ip, puerto);
            //System.out.println("Conectado a :" + socket.getInetAddress().getHostName());
            mainAplication.abrir_ventana_chat();
            ventana2_controller.nombre_usuario(String.valueOf(socket.getLocalAddress().getHostName()));

        } catch (Exception e) {
            //System.out.println("Excepción al levantar conexión: " + e.getMessage());
        }
    }

    public void enviar(String mensaje){
        try {
            salida.writeUTF(mensaje);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void recibir(String mensaje){
        try {
            entrada.readUTF();
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

    public void ejecutar(String ip, int puerto){
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                Conexion(ip, puerto);
                try {
                    entrada = new DataInputStream(socket.getInputStream());
                    salida = new DataOutputStream(socket.getOutputStream());
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
