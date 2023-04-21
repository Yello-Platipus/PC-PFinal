package Oyentes;

import Cliente.*;
import Mensaje.MensajeActualizar;

import java.io.*;
import java.net.Socket;

public class ClienteReceptor extends Thread {
    private Cliente cliente;
    private InputStream in;
    private OutputStream out;
    private Socket socket;
    private FileOutputStream archivo;
    private String ip;
    private int puerto;
    private ObjectOutputStream outServidor;
    private String fichero;

    public ClienteReceptor(Cliente c, String ip, int puerto, String fichero, ObjectOutputStream outServer){
        try {
            this.outServidor = outServer;
            this.ip = ip;
            this.puerto = puerto;
            cliente = c;
            this.fichero = fichero;
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


            outServidor.writeObject(new MensajeActualizar(cliente.getId(),"Servidor", fichero));
            cliente.actualizarListaUsuarios();
            archivo.close();
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
