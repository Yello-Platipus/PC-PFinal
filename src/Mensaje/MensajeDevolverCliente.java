package Mensaje;

import java.net.ServerSocket;

public class MensajeDevolverCliente extends Mensaje{//TODO cambiar
    String ip;
    int puerto;
    public MensajeDevolverCliente(String origen, String destino, String ip, int puerto) {
        super(origen, destino);
        this.ip = ip;
        this.puerto = puerto;
    }
    @Override
    public String getTipo() {
        return "DevolverCliente";
    }

    @Override
    public String getOrigen() {
        return null;
    }

    @Override
    public String getDestino() {
        return null;
    }

    public String getIp(){
        return ip;
    }

    public int getPuerto(){
        return puerto;
    }
}
