package Mensaje;

public class MensajeCerrarConexion extends Mensaje{
    @Override
    public String getTipo() {
        return "CerrarConexion";
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
