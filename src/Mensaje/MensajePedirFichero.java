package Mensaje;

public class MensajePedirFichero extends Mensaje{
    @Override
    public String getTipo() {
        return "PedirFichero";
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