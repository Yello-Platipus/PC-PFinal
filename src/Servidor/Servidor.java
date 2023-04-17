package Servidor;

import Cliente.Usuario;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.*;

public class Servidor {
    private ServerSocket serverSocket;
    private InetAddress ip;
    private int puerto;
    private Set<Usuario> conectados;
    private Map<String, Usuario> quienTiene;
    private ArrayList<Thread> hilos;

    public Servidor(InetAddress ip, int puerto) {
        try {
            this.serverSocket = new ServerSocket(puerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.ip = ip;
        this.puerto = puerto;
        conectados = new HashSet<>();
        hilos = new ArrayList<>();
        quienTiene = new HashMap<>();
    }
}
