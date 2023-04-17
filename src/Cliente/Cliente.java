package Cliente;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
    private Usuario usuario;
    private Socket socket;
    private InputStream in;
    private OutputStream out;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
        try {
            this.socket = new Socket("localhost", usuario.getPuerto());
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
