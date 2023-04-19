package Oyentes;

import Cliente.Cliente;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

public class ClienteEmisor extends Thread {
    private Cliente cliente;
    private InputStream in;
    private OutputStream out;
    private ServerSocket serverSocket;

    public ClienteEmisor(){
        serverSocket = new ServerSocket()
    }
}
