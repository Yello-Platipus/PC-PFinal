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
    private Map<String, Usuario> quienTiene;

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

    public static int getPuerto() {
        return puerto;
    }

    private void start(int puerto) throws IOException {
        Socket cliente;
        while(true){
            cliente = serverSocket.accept();
            new OyenteCliente(cliente, this).start();

        }
    }

}
