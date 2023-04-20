package Oyentes;

import Mensaje.*;
import Servidor.Servidor;
import javafx.util.Pair;

import java.io.*;
import java.net.Socket;

public class OyenteCliente extends Thread {
    InputStream is;
    OutputStream os;
    Servidor se;

    public OyenteCliente(Socket clienteSo, Servidor server) {

        try {
            clienteSo.setSoTimeout(0);
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

            if(men.getTipo() == TiposMensajes.CONEXION){
                MensajePedirConexion aux = (MensajePedirConexion) men;
                se.anadirUsuario(aux.getId(),in,out,aux.getInfo());
                out.writeObject(new MensajeOkConexion(aux.getDestino(),aux.getOrigen()));

                while(men.getTipo() != TiposMensajes.CERRAR_CONEXION){
                    men = (Mensaje) in.readObject();

                    switch (men.getTipo()){
                        case LISTA_USUARIOS:
                            out.writeObject(new MensajeOkListaUsuarios(men.getDestino(),men.getOrigen(),se.getInfo()));
                            break;
                        case PEDIR_FICHERO:
                            MensajePedirFichero aux1 = (MensajePedirFichero) men;
                            Pair<ObjectInputStream, ObjectOutputStream> par = null;
                            try {
                                par = se.getUsuario(aux1.getFichero());//1- Buscar al cliente con lo que pide el cliente 1
                            }
                            catch (RuntimeException e){
                                e.printStackTrace();
                                break;
                            }
                            //2- Con el in y el out de ese cliente 2 encontrado
                            par.getValue().writeObject(new MensajeEmitirFichero(aux1.getDestino(),aux1.getOrigen(),aux1.getFichero()));//3- Enviarle un mensaje de tipo "PedirSocket" con el socket del cliente 2

                            break;
                        case OK_EMITIR_FICHERO:
                            MensajeOkEmitirFichero aux3 = (MensajeOkEmitirFichero) in.readObject();//4- El cliente 2 recibe el mensaje y le responde con un mensaje de tipo "DevolverSocket"
                            Pair<ObjectInputStream, ObjectOutputStream> parAux = null;
                            try {
                                parAux = se.getUsuario(aux3.getFichero());//1- Buscar al cliente con lo que pide el cliente 1
                            }
                            catch (RuntimeException e){
                                e.printStackTrace();
                                break;
                            }
                            parAux.getValue().writeObject(aux3);//5- Enviar el mensaje de tipo "DevolverSocket" al cliente 1
                        case CERRAR_CONEXION:
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
