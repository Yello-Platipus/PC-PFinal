package Servidor;

import Cliente.Usuario;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.*;

public class Servidor {
    private ServerSocket serverSocket;
    private static final int puerto = 8080;
    private Set<Usuario> conectados;
    private Map<String, Usuario> quienTiene;
    private ArrayList<Thread> hilos;

    public Servidor() {
        try {
            this.serverSocket = new ServerSocket(puerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conectados = new HashSet<>();
        hilos = new ArrayList<>();
        quienTiene = new HashMap<>();
    }

    public static int getPuerto() {
        return puerto;
    }
}
