package Mensaje;

import java.net.ServerSocket;

public class MensajePedirSocket extends Mensaje{

    public MensajePedirSocket (String origen, String destino){
        super(origen, destino);
    }
    @Override
    public String getTipo() {
        return "DevolverSocket";
    }

    @Override
    public String getOrigen() {
        return null;
    }

    @Override
    public String getDestino() {
        return null;
    }
}