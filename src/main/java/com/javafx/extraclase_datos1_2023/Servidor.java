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
            //System.out.println("Esperando conexión entrante en el puerto " + String.valueOf(puerto) + "...");

            socket = serverSocket.accept();
            //System.out.println("Conexión establecida con: " + socket.getInetAddress().getHostName() + "\n\n\n");



        } catch (Exception e) {
            System.out.println("error al iniciar conexion");
            System.exit(0);
        }
    }}


