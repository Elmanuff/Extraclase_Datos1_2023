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
            System.out.println("Conectado a :" + socket.getInetAddress().getHostName());
            System.out.println("cliente");

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

    public void recibir() {
        try {
            entrada.readUTF();

            if (entrada.readUTF() !=null) {
                ventana2_controller.recibir_mensaje(entrada.readUTF());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrar_Conexion(){
        try {
            entrada.close();
            salida.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ejecutar(String ip, int puerto) throws IOException {
        mainAplication.abrir_ventana_chat(false);

        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Conexion(ip, puerto);
                    abrirFlujos();
                    recibir();
                } finally {
                    cerrar_Conexion();
                }
            }
        });
        hilo.start();
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
}
