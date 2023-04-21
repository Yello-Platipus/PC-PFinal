package Cliente;

import java.io.*;
import java.net.Socket;
import java.util.Set;

import Cliente.Locks.LockTicket;
import GUI.MainWindowCliente;
import Mensaje.MensajeCerrarConexion;
import Mensaje.MensajePedirConexion;
import Mensaje.MensajePedirFichero;
import Mensaje.MensajePedirListaUsuarios;
import Oyentes.OyenteServidor;
import Servidor.Servidor;

public class Cliente {
    private Usuario usuario;
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private ObjectOutputStream objetoOut;
    private ObjectInputStream objetoIn;
    private LockTicket lock;

    public Cliente(Usuario usuario) {
        this.usuario = usuario;
        try {
            this.socket = null;
            in = null;
            out = null;
            lock = new LockTicket();
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
        objetoOut.writeObject(new MensajePedirListaUsuarios(getId(),"Servidor"));
    }

    public void enviarPedirFichero(String fichero) throws IOException {
        objetoOut.writeObject(new MensajePedirFichero(getId(),"Servidor",fichero));
    }

    public void actualizarListaUsuarios(){
        lock.lock();
        usuario.actualizarInfo();
        lock.unlock();
    }

    public void cerrarSesion() throws IOException {
        lock.lock();
        String[] matriz = new String[usuario.getInfo().size()];
        int i = 0;
        for (String cadena : usuario.getInfo()) {
            matriz[i] = cadena;
            i++;
        }
        lock.unlock();
        objetoOut.writeObject(new MensajeCerrarConexion(getId(),"Servidor", usuario.getId(), matriz));
    }



    public String getIp(){
        return usuario.getIp().getHostAddress();
    }

}
