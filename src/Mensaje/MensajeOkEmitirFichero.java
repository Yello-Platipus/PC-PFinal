package Mensaje;

import java.net.InetAddress;

public class MensajeOkEmitirFichero extends Mensaje{//TODO cambiar
    InetAddress ip;
    int puerto;
    public MensajeOkEmitirFichero(String origen, String destino, InetAddress ip, int puerto) {
        super(origen, destino,TiposMensajes.OK_EMITIR_FICHERO);
        this.ip = ip;
        this.puerto = puerto;
    }

    public InetAddress getIp(){
        return ip;
    }

    public int getPuerto(){
        return puerto;
    }
}
