package Oyentes;


import Mensaje.*;
import Cliente.Cliente;

import java.io.*;
import java.net.Socket;

public class OyenteServidor extends Thread {
    InputStream is;
    OutputStream os;

    public OyenteServidor(Socket serverSo, Cliente client) {

        try {
            serverSo.setSoTimeout(0);
            is = serverSo.getInputStream();
            os = serverSo.getOutputStream();
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
                men = (Mensaje) in.readObject();
                switch (men.getTipo()){
                    case "PedirListaUsuarios":
                        out.writeObject(new MensajeDevolverListaUsuarios());
                        break;
                    case "PedirFichero":
                        //TODO cosas con el cliente 2
                        //Enviarle mensaje al cliente 2 pidiendo el fichero (socket)
                        break;
                    case "CerrarConexion":
                        //TODO borrar al cliente 1 de la lista de clientes
                        break;
                    case "DevolverSocket":
                        //TODO cosas con el cliente 2
                        break;
                }

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
