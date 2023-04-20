package Mensaje;

import java.net.InetAddress;

public class MensajeOkEmitirFichero extends Mensaje{//TODO cambiar
    InetAddress ip;
    int puerto;
    String aDonde;
    public MensajeOkEmitirFichero(String origen, String destino, InetAddress ip, int puerto,String deDonde) {
        super(origen, destino,TiposMensajes.OK_EMITIR_FICHERO);
        this.ip = ip;
        this.puerto = puerto;
        aDonde = deDonde;
    }

    public InetAddress getIp(){
        return ip;
    }

    public int getPuerto(){
        return puerto;
    }

    public String getADonde(){
        return aDonde;
    }
}
