package Servidor;

import Cliente.Usuario;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.*;

public class Servidor {
    private ServerSocket serverSocket;
    private InetAddress ip;
    private static final int puerto = 8080;
    private Set<Usuario> conectados;
    private Map<String, Usuario> quienTiene;
    private ArrayList<Thread> hilos;

    public Servidor(InetAddress ip) {
        try {
            this.serverSocket = new ServerSocket(puerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.ip = ip;
        conectados = new HashSet<>();
        hilos = new ArrayList<>();
        quienTiene = new HashMap<>();
    }
}
