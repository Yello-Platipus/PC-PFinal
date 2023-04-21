package Oyentes;


import GUI.MainWindowCliente;
import Mensaje.*;
import Cliente.Cliente;
import Util.GeneradorPuertos;

import java.io.*;


public class OyenteServidor extends Thread {

    Cliente cliente;
    ObjectInputStream is;
    ObjectOutputStream out;
    public OyenteServidor( ObjectInputStream is, ObjectOutputStream out, Cliente cliente) {

        this.is = is;
        this.out = out;
        this.cliente = cliente;

    }

    public void run(){
        //TODO Hay que ver si se hace con un solo hilo, como el de OyenteCliente o para cada
        try {

            Mensaje men = (Mensaje) is.readObject();

            if(men.getTipo() == TiposMensajes.OK_CONEXION){
                while(men.getTipo() != TiposMensajes.CERRAR_CONEXION){
                    men = (Mensaje) is.readObject();

                    switch (men.getTipo()){
                        case CERRAR_CONEXION:
                            break;//TODO ?¿?¿?¿?
                        case OK_LISTA_USUARIOS:
                            MensajeOkListaUsuarios aux = (MensajeOkListaUsuarios) men;
                            new MainWindowCliente(cliente,aux.getListaUsuarios());
                            break;
                        case EMITIR_FICHERO:
                            MensajeEmitirFichero aux2 = (MensajeEmitirFichero) men;
                            int puerto2 = GeneradorPuertos.nuevoPuerto();
                            new ClienteEmisor(cliente,puerto2,aux2.getFichero(),out,aux2).start();
                            break;
                        case OK_EMITIR_FICHERO:
                            MensajeOkEmitirFichero aux3 = (MensajeOkEmitirFichero) men;
                            int puerto = aux3.getPuerto();
                            String ip = aux3.getIp();
                            new ClienteReceptor(cliente,ip,puerto,aux3.getFichero(),out).start();

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
