package Mensaje;

public class MensajeConexion extends Mensaje{
    @Override
    public String getTipo() {
        return "Conexion";
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
