package Mensaje;

public class MensajePedirCliente extends Mensaje{

    public MensajePedirCliente(String origen, String destino){
        super(origen, destino);
    }
    @Override
    public String getTipo() {
        return "PedirCliente";
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