package Oyentes;


import Mensaje.*;
import Cliente.Cliente;
import javafx.util.Pair;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class OyenteServidor extends Thread {
    InputStream is;
    OutputStream os;
    Cliente client;
    public OyenteServidor(Socket serverSo, Cliente client) {

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

            Mensaje men = (Mensaje) in.readObject();

            if(men.getTipo().equals("OK")){


                while(true){
                    men = (Mensaje) in.readObject();

                    switch (men.getTipo()){
                        case "CerrarConexion":
                            break;//TODO ?¿?¿?¿?
                        case "DevolverListaUsuarios":
                            MensajeDevolverListaUsuarios aux = (MensajeDevolverListaUsuarios) men;
                            client.setFicherosExternos(aux.getListaUsuarios());
                            break;
                        case "PedirCliente":
                            MensajePedirCliente aux2 = (MensajePedirCliente) men;

                            break;
                        default://TODO MENSAJE ERROR?
                            System.out.println("Mensaje no reconocido");
                    }
                }
            }


        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}
