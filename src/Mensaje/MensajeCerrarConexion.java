package Mensaje;

public class MensajeCerrarConexion extends Mensaje{
    @Override
    public int getTipo() {
        return 0;
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