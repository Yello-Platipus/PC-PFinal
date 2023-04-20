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

    public ClienteReceptor(Cliente c, String ip, int puerto, String fichero){
        try {
            cliente = c;
            socket = new Socket(ip, puerto);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            archivo = new FileOutputStream(Usuario.ruta + cliente.getId() + "/" + fichero);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                archivo.write(buffer, 0, bytesRead);
            }

            archivo.close();
            in.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
