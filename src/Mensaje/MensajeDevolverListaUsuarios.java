package Mensaje;

public class MensajeDevolverListaUsuarios extends Mensaje{
    @Override
    public String getTipo() {
        return "DevolverListaUsuarios";
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
