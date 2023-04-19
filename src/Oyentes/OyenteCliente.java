package Oyentes;

import Mensaje.*;
import Servidor.Servidor;
import javafx.util.Pair;

import java.io.*;
import java.net.Socket;

public class OyenteCliente extends Thread{
    InputStream is;
    OutputStream os;
    Servidor se;
    public OyenteCliente(Socket clienteSo, Servidor server) {

        try {
            is = clienteSo.getInputStream();
            os = clienteSo.getOutputStream();
            se = server;
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
                MensajePedirConexion aux = (MensajePedirConexion) men;
                se.anadirUsuario(aux.getId(),in,out,aux.getInfo());
                out.writeObject(new MensajeOK(men.getDestino(),men.getOrigen()));

                while(!men.getTipo().equals("CerrarConexion")){

                    men = (Mensaje) in.readObject();

                    switch (men.getTipo()){
                        case "PedirListaUsuarios"://TODO
                            out.writeObject(new MensajeDevolverListaUsuarios(men.getDestino(),men.getOrigen(),se.getInfo()));
                            break;
                        case "PedirFichero":
                            MensajePedirFichero aux1 = (MensajePedirFichero) men;
                            Pair<InputStream, OutputStream> par = null;
                            try {
                                par = se.getUsuario(aux1.getFichero());//1- Buscar al cliente con lo que pide el cliente 1
                            }
                            catch (RuntimeException e){
                                e.printStackTrace();
                                break;
                            }
                            ObjectInputStream inCliente2 = new ObjectInputStream(par.getKey());//2- Con el in y el out de ese cliente 2 encontrado
                            ObjectOutputStream outCliente2 = new ObjectOutputStream(par.getValue());
                            outCliente2.writeObject(new MensajePedirSocket(aux1.getDestino(),aux1.getOrigen()));//3- Enviarle un mensaje de tipo "PedirSocket" con el socket del cliente 2
                            MensajeDevolverSocket aux3 = (MensajeDevolverSocket) inCliente2.readObject();//4- El cliente 2 recibe el mensaje y le responde con un mensaje de tipo "DevolverSocket"
                            out.writeObject(aux3);//5- Enviar el mensaje de tipo "DevolverSocket" al cliente 1
                            break;
                        case "CerrarConexion":
                            MensajeCerrarConexion aux2 = (MensajeCerrarConexion) men;
                            se.eliminarUsuario(aux2.getId(),aux2.getInfo());
                            break;
                        default://TODO MENSAJE ERROR?
                            System.out.println("Mensaje no reconocido");
                    }
                }


            }
            else{//TODO MENSAJE ERROR?
                System.out.println("Mensaje recibido incorrecto (no fue de entablar conexion)");
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}
