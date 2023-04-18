package Mensaje;

public class MensajePedirConexion extends Mensaje{
    @Override
    public String getTipo() {
        return "PedirConexion";
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
