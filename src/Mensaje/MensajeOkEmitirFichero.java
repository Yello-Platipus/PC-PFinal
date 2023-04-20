package Mensaje;

import java.net.InetAddress;

public class MensajeOkEmitirFichero extends Mensaje{//TODO cambiar
    String ip;
    int puerto;
    String fichero;
    public MensajeOkEmitirFichero(String origen, String destino, String ip, int puerto,String fichero) {
        super(origen, destino,TiposMensajes.OK_EMITIR_FICHERO);
        this.ip = ip;
        this.puerto = puerto;
        this.fichero = fichero;
    }

    public String getIp(){
        return ip;
    }

    public int getPuerto(){
        return puerto;
    }

    public String getFichero(){
        return fichero;
    }
}
