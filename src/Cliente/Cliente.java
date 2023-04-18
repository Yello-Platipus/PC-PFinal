package Cliente;

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
            this.socket = new Socket("localhost", Servidor.getPuerto());
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
