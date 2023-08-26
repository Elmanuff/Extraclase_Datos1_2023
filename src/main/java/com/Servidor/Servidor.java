package com.Servidor;

import javafx.application.Platform;
import java.net.*;
import java.io.*;

/**
 * Esta clase controla todo lo necesario para el correcto
 * funcionamiento del servidor, entradas
 * salidas y lo que tiene que ver con el socket
 */
public class Servidor {
    public Socket socket;
    public DataInputStream entrada_servidor;
    public DataOutputStream salida_servidor;

    /**
     * Esta funcion ejecuta el hilo que permite que el servidor este siempre escuchando los datos que envia el cliente por medio del socket.
     * @param puerto Es el puerto que se le brinda para poner inicializar la conexion.
     */
    public void ejecutar(int puerto){
        Thread hilo = new Thread(() -> {
            try {
                conexion(puerto);
                Platform.runLater(() -> {
                    try {
                        com.Servidor.MainServidor.abrirVentanaChat();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                abrirFlujos();
                recibir();
            } finally {
                cerrarConexion();
                Platform.exit();
            }
        });
        hilo.start();
    }

    /**
     * Verifica el socket con el que se va a conectar el cliente y genera los prints para mantener un mejor control.
     * @param puerto Es el puerto que se le brinda para poner inicializar la conexion.
     */
    public void conexion(int puerto) {
        try {
            try (ServerSocket serverSocket = new ServerSocket(puerto)) {
                System.out.println("Esperando conexión en el puerto " + puerto + "...");
                socket = serverSocket.accept();
                System.out.println("Conexión establecida con: " + socket.getInetAddress().getHostName());
            }
        } catch (Exception e) {
            System.out.println("Error al iniciar conexion");
        }
    }

    /**
     * Genera las puertas que sirven para recibir los datos y enviar los datos.
     */
    public void abrirFlujos() {
        try {
            entrada_servidor = new DataInputStream(socket.getInputStream());
            salida_servidor = new DataOutputStream(socket.getOutputStream());
            salida_servidor.flush();
        } catch (IOException e) {
            System.out.println("Error al abrir flujos");
        }
    }

    /**
     * Permite enviar el mensaje que se escribe.
     * @param mensaje es el mensaje de texto puesto en el textbox para poder enviarlo al cliente.
     */
    public void enviar(String mensaje){
        try {
            salida_servidor.writeUTF(mensaje);
            salida_servidor.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Permite recibir el mensaje y enviarlo a la ventana de chat para que sea puesto en esta.
     */
    public void recibir(){
        try {
            while (true)
            {
                String mensaje_recibido_servidor = entrada_servidor.readUTF();
                Platform.runLater(() -> com.Servidor.MainServidor.ventanaServidorController.recibirMensaje(mensaje_recibido_servidor));

            }
        } catch (EOFException e) {
            System.out.println("Conexión cerrada por el cliente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cierra la conexion del servidor.
     */
    public void cerrarConexion(){
        try {
            salida_servidor.close();
            entrada_servidor.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
