package Oyentes;

import Cliente.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClienteReceptor extends Thread {
    private Cliente cliente;
    private InputStream in;
    private OutputStream out;
    private Socket socket;
    private FileOutputStream archivo;
    private String ip;
    private int puerto;

    public ClienteReceptor(Cliente c, String ip, int puerto, String fichero){
        try {
            this.ip = ip;
            this.puerto = puerto;
            cliente = c;
            archivo = new FileOutputStream(Usuario.ruta + cliente.getId() + "/" + fichero, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            socket = new Socket(ip, puerto);
            socket.setSoTimeout(0);
            in = socket.getInputStream();
            out = socket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1)
                archivo.write(buffer, 0, bytesRead);

            archivo.close();
            in.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
