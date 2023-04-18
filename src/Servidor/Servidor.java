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
    private InetAddress ip;
    private static final int puerto = 8080;
    private Set<Usuario> conectados;
    private Map<String, Usuario> quienTiene;
    private ArrayList<Thread> hilos;

    public Servidor(InetAddress ip) throws IOException {
        try {
            this.serverSocket = new ServerSocket(puerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.ip = ip;
        conectados = new HashSet<>();
        hilos = new ArrayList<>();
        quienTiene = new HashMap<>();
        start(puerto);
    }

    private void start(int puerto) throws IOException {
        Socket cliente;
        while(true){
            cliente = serverSocket.accept();
            OyenteCliente oy = new OyenteCliente(cliente);
            hilos.add(oy);
            oy.start();
        }


    }
}