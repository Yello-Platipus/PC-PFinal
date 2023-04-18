package Mensaje;

public class MensajeOK extends Mensaje{
    @Override
    public String getTipo() {
        return "OK";
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
