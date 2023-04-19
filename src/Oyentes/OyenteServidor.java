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
    Cliente cliente;

    public OyenteServidor(Socket serverSo, Cliente cliente) {

        conectado = false;
        try {
            this.cliente = cliente;
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

            out.writeObject(new MensajePedirConexion("Cliente","Servidor",cliente.getId(),cliente.getInfo()));

            Mensaje men = (Mensaje) in.readObject();

            if(men.getTipo().equals("OK")){
                conectado = true;
            }
            else{
                System.out.println("Error al conectarse al servidor");
            }
            while(true){
                men = (Mensaje) in.readObject();
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
