package Mensaje;

public class MensajeOK extends Mensaje{

    public MensajeOK(String origen, String destino) {
        super(origen, destino);
    }
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
