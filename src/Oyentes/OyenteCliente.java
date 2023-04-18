package Oyentes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class OyenteCliente extends Thread{
    InputStream is;
    OutputStream os;

    public OyenteCliente(Socket clienteSo) {

        try {
            is = clienteSo.getInputStream();
            os = clienteSo.getOutputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){

    }
}
