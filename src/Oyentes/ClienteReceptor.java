package Oyentes;

import Cliente.Cliente;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClienteReceptor extends Thread {
    private Cliente cliente;
    private InputStream in;
    private OutputStream out;
    private Socket socket;

    public ClienteReceptor(){

    }
}
