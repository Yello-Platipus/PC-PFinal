package Oyentes;

import Cliente.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClienteEmisor extends Thread {
    private Cliente cliente;
    private OutputStream out;
    private ServerSocket serverSocket;
    private Socket socket;
    private FileInputStream archivo;

    public ClienteEmisor(Cliente c, int puerto, String fichero) {
        try {
            cliente = c;
            serverSocket = new ServerSocket(puerto);
            archivo = new FileInputStream(Usuario.ruta + cliente.getId() + "/" + fichero);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
