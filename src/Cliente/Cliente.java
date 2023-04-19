package Cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

import Mensaje.MensajePedirConexion;
import Oyentes.OyenteCliente;
import Oyentes.OyenteServidor;
import Servidor.Servidor;

public class Cliente {
    private Usuario usuario;
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private OyenteServidor conexion;
    private ObjectOutputStream objetoOut;
    private ArrayList<String> ficherosExternos;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
        try {
            this.socket = null;
            in = null;
            out = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void conectar() {
        try {
            socket = new Socket(Servidor.Host, Servidor.getPuerto());
            new OyenteServidor(socket, this);
            conexion.start();
            in = socket.getInputStream();
            out = socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getId(){
        return usuario.getId();
    }

    public Set<String> getInfo(){
        return usuario.getInfo();
    }

    public void enviar() throws IOException {
        objetoOut = new ObjectOutputStream(out);
        objetoOut.writeObject(new MensajePedirConexion("Cliente","Servidor",getId(),getInfo()));
    }
    public void setFicherosExternos(ArrayList<String> ficheros){
        ficherosExternos = ficheros;
    }
}
