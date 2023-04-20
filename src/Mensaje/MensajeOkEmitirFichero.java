package Mensaje;

import java.net.InetAddress;

public class MensajeOkEmitirFichero extends Mensaje{//TODO cambiar
    String ip;
    int puerto;
    String IdReceptor;
    String fichero;
    public MensajeOkEmitirFichero(String origen, String destino, String ip, int puerto,String IdReceptor, String fichero) {
        super(origen, destino,TiposMensajes.OK_EMITIR_FICHERO);
        this.ip = ip;
        this.puerto = puerto;
        this.IdReceptor = IdReceptor;
        this.fichero = fichero;
    }

    public String getIp(){
        return ip;
    }

    public int getPuerto(){
        return puerto;
    }

    public String getIdReceptor(){
        return IdReceptor;
    }

    public String getFichero(){
        return fichero;
    }
}
