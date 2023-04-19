package Servidor;

import Cliente.Usuario;
import Oyentes.OyenteCliente;
import javafx.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Servidor {
    private ServerSocket serverSocket;
    private static final int puerto = 8080;
    public static final String Host = "localhost";
    private Map<String, Pair<InputStream, OutputStream>> entradaSalidaUsers;
    private Map<String, ArrayList<String>> quienTiene;

    public Servidor() throws IOException {
        try {
            this.serverSocket = new ServerSocket(puerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        entradaSalidaUsers = new HashMap<>();
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
    public void end() throws IOException {
        serverSocket.close();
    }

    public synchronized void anadirUsuario(String id, InputStream entrada, OutputStream salida,Set<String> elems){
        entradaSalidaUsers.put(id, new Pair<>(entrada, salida));
        for(String archivo : elems)
            quienTiene.get(archivo).add(id);
    }

    public synchronized void eliminarUsuario(String id,Set<String> elems){
        entradaSalidaUsers.remove(id);
        for(String archivo : elems)
            quienTiene.get(archivo).remove(id);
    }

    public synchronized ArrayList<String> getInfo(){
        return new ArrayList<>(quienTiene.keySet());
    }

    public synchronized Pair<InputStream, OutputStream> getUsuario(String fichero) throws RuntimeException{
        ArrayList<String> idUsuarios = quienTiene.get(fichero);
        if(idUsuarios.size() == 0)
            throw new RuntimeException("No hay usuarios que tengan el archivo");
        else
            return entradaSalidaUsers.get(idUsuarios.get(0));
    }

    public static int getPuerto() {
        return puerto;
    }

}
