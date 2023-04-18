package Servidor;

import Cliente.Usuario;
import Oyentes.OyenteCliente;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Servidor {
    private ServerSocket serverSocket;
    private static final int puerto = 8080;
    private Set<Usuario> conectados;
    private Map<String, ArrayList<Usuario>> quienTiene;

    public Servidor() throws IOException {
        try {
            this.serverSocket = new ServerSocket(puerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conectados = new HashSet<>();
        quienTiene = new HashMap<>();
        start(puerto);
    }

    private void start(int puerto) throws IOException {
        Socket cliente;
        while(true){
            cliente = serverSocket.accept();
            new OyenteCliente(cliente, this).start();

        }
    }

    public synchronized void anadirUsuario(Usuario usuario){
        conectados.add(usuario);
        for(String archivo : usuario.getInfo())
            quienTiene.get(archivo).add(usuario);
    }

    public synchronized void eliminarUsuario(Usuario usuario){
        conectados.remove(usuario);
        for(String archivo : usuario.getInfo())
            quienTiene.get(archivo).remove(usuario);
    }

    public synchronized ArrayList<String> getInfo(){
        return new ArrayList<>(quienTiene.keySet());
    }

    public synchronized Usuario getUsuario(String info){
        // TODO puede que haya que devolver un Socket/ServerSocket en vez de un Usuario
        ArrayList<Usuario> usuarios = quienTiene.get(info);
        if(usuarios.size() == 0)
            return null;
        else
            return usuarios.get(0);
    }

    public static int getPuerto() {
        return puerto;
    }

}
