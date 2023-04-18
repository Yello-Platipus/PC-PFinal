package Cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import Servidor.Servidor;

public class Cliente {
    private Usuario usuario;
    private ServerSocket server;
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
        try {
            server = new ServerSocket(usuario.getPuerto());
            this.socket = null;
            in = null;
            out = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void conectar() {
        try {
            socket = new Socket("localhost", Servidor.getPuerto());
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
