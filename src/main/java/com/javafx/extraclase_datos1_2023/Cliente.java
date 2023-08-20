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

            salida = (DataOutputStream) socket.getOutputStream();
            salida.writeUTF("mensaje");

        } catch (Exception e) {
            //System.out.println("Excepción al levantar conexión: " + e.getMessage());
        }
    }
}
