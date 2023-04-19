package Mensaje;

import java.net.ServerSocket;

public class MensajeDevolverSocket extends Mensaje{//TODO cambiar
    ServerSocket socket;
    public MensajeDevolverSocket (String origen, String destino, ServerSocket socket){
        super(origen, destino);
        this.socket = socket;
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

    public ServerSocket getSocket(){
        return socket;
    }
}
