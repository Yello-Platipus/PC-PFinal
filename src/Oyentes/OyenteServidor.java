package Oyentes;


import Mensaje.*;
import Cliente.Cliente;
import Util.GeneradorPuertos;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class OyenteServidor extends Thread {
    InputStream is;
    OutputStream os;
    Cliente cliente;
    public OyenteServidor(Socket serverSo, Cliente cliente) {

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

            Mensaje men = (Mensaje) in.readObject();

            if(men.getTipo() == TiposMensajes.OK_CONEXION){


                while(men.getTipo() != TiposMensajes.CERRAR_CONEXION){
                    men = (Mensaje) in.readObject();

                    switch (men.getTipo()){
                        case CERRAR_CONEXION:
                            break;//TODO ?¿?¿?¿?
                        case OK_LISTA_USUARIOS:
                            MensajeOkListaUsuarios aux = (MensajeOkListaUsuarios) men;
                            cliente.setFicherosExternos(aux.getListaUsuarios());
                            break;
                        case OK_EMITIR_FICHERO:
                            MensajeOkEmitirFichero aux3 = (MensajeOkEmitirFichero) men;
                            int puerto = aux3.getPuerto();
                            InetAddress ip = aux3.getIp();
                            new ClienteReceptor(puerto,ip,aux3.getDeDonde()).start();
                        case EMITIR_FICHERO:
                            MensajeEmitirFichero aux2 = (MensajeEmitirFichero) men;
                            ServerSocket s = new ServerSocket(GeneradorPuertos.nuevoPuerto());
                            out.writeObject(new MensajeOkEmitirFichero(men.getDestino(),men.getOrigen(),s.getInetAddress(), s.getLocalPort(),aux2.getDeDonde()));
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
