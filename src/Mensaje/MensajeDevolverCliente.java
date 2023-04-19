package Mensaje;

import sun.security.x509.IPAddressName;

import java.net.InetAddress;
import java.net.ServerSocket;

public class MensajeDevolverCliente extends Mensaje{//TODO cambiar
    InetAddress ip;
    int puerto;
    public MensajeDevolverCliente(String origen, String destino, InetAddress ip, int puerto) {
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

    public InetAddress getIp(){
        return ip;
    }

    public int getPuerto(){
        return puerto;
    }
}
