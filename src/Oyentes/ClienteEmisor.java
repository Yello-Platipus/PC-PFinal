package Oyentes;

import Cliente.*;
import Mensaje.MensajeEmitirFichero;
import Mensaje.MensajeOkEmitirFichero;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClienteEmisor extends Thread {
    private Cliente cliente;
    private OutputStream out;
    private ServerSocket serverSocket;
    private Socket socket;
    private FileInputStream archivo;
    private ObjectOutputStream outServidor;
    int puerto;
    MensajeEmitirFichero aux2;
    public ClienteEmisor(Cliente c, int puerto, String fichero, ObjectOutputStream outServer, MensajeEmitirFichero aux2) {
        try {
            this.aux2 = aux2;
            this.puerto = puerto;
            this.outServidor = outServer;
            cliente = c;
            serverSocket = new ServerSocket(puerto);
            archivo = new FileInputStream(Usuario.ruta + cliente.getId() + "/" + fichero);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            outServidor.writeObject(new MensajeOkEmitirFichero(aux2.getDestino(),aux2.getOrigen(),cliente.getIp(),puerto,aux2.getIdReceptor(), aux2.getFichero()));
            socket = serverSocket.accept();
            socket.setSoTimeout(0);
            out = socket.getOutputStream();
            // Puede que la ruta de archivo este mal(?)

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = archivo.read(buffer)) != -1)
                out.write(buffer, 0, bytesRead);

            archivo.close();
            out.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
