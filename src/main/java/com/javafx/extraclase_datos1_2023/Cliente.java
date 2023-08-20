package com.javafx.extraclase_datos1_2023;

import java.net.*;
import java.io.*;

public class Cliente {
    public Socket socket;
    public DataInputStream entrada;
    public DataOutputStream salida;
    public void ejecutar(String ip, int puerto) throws IOException {
        mainAplication.abrir_ventana_chat(false);

        Thread hilo = new Thread(() -> {
            try {
                Conexion(ip, puerto);
                abrirFlujos();
                recibir();
            } finally {
                cerrar_Conexion();
            }
        });
        hilo.start();
    }

    public void Conexion(String ip, int puerto) {
        try {
            socket = new Socket(ip, puerto);
            System.out.println("Conectado a :" + socket.getInetAddress().getHostName());

        } catch (IOException e) {
            System.out.println("Excepción al levantar conexión: " + e.getMessage());
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
            salida.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void recibir() {
        try {
            entrada.readUTF();
            mainAplication.ventana2Controller.recibir_mensaje(entrada.readUTF());

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
}