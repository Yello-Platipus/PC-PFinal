package Cliente;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

import GUI.MainWindowCliente;
import Mensaje.MensajePedirConexion;
import Mensaje.MensajePedirFichero;
import Mensaje.MensajePedirListaUsuarios;
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
    private ObjectInputStream objetoIn;
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
            socket.setSoTimeout(0);
            out = socket.getOutputStream();
            in = socket.getInputStream();

            objetoOut = new ObjectOutputStream(out);
            objetoIn = new ObjectInputStream(in);


            new OyenteServidor(objetoIn,objetoOut, this).start();
            objetoOut.writeObject(new MensajePedirConexion(getId(),"Servidor",getId(),getInfo()));
            new MainWindowCliente(this);
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

    public void enviarPedirLista() throws IOException {
        objetoOut = new ObjectOutputStream(out);
        objetoOut.writeObject(new MensajePedirListaUsuarios(getId(),"Servidor"));
    }

    public void enviarPedirFichero(String fichero) throws IOException {
        objetoOut = new ObjectOutputStream(out);
        objetoOut.writeObject(new MensajePedirFichero(getId(),"Servidor",fichero));
    }

    public void setFicherosExternos(ArrayList<String> ficheros){
        ficherosExternos = ficheros;
    }

    public String getIp(){
        return usuario.getIp().getHostAddress();
    }
}
