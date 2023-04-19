package Oyentes;


import Mensaje.*;
import Cliente.Cliente;
import javafx.util.Pair;

import java.io.*;
import java.net.Socket;

public class OyenteServidor extends Thread {
    InputStream is;
    OutputStream os;
    boolean conectado;
    Cliente client;
    public OyenteServidor(Socket serverSo, Cliente client) {

        conectado = false;
        try {
            this.client = client;
            serverSo.setSoTimeout(0);
            is = serverSo.getInputStream();
            os = serverSo.getOutputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        //TODO Hay que ver si se hace con un solo hilo, como el de OyenteCliente o para cada
        try {
            ObjectInputStream in = new ObjectInputStream(is);
            ObjectOutputStream out = new ObjectOutputStream(os);

            out.writeObject(new MensajePedirConexion("Cliente","Servidor",client.getId(),client.getInfo()));

            Mensaje men = (Mensaje) in.readObject();

            if(men.getTipo().equals("OK")){
                conectado = true;
            }
            else{
                System.out.println("Error al conectarse al servidor");
            }

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void pedirListaUsuarios(){
        try {
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(new MensajePedirListaUsuarios("Cliente","Servidor"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean conectado() {
        return conectado;
    }

}
