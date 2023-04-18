package Oyentes;

import Mensaje.*;
import Servidor.Servidor;

import java.io.*;
import java.net.Socket;

public class OyenteCliente extends Thread{
    InputStream is;
    OutputStream os;

    public OyenteCliente(Socket clienteSo, Servidor server) {

        try {
            is = clienteSo.getInputStream();
            os = clienteSo.getOutputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            ObjectInputStream in = new ObjectInputStream(is);
            ObjectOutputStream out = new ObjectOutputStream(os);

            Mensaje men = (Mensaje) in.readObject();
            if(men.getTipo().equals("PedirConexion")){
                out.writeObject(new MensajeOK());


            }
            else{
                //TODO Mensaje de error por GUI
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}
