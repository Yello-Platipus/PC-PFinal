package Mensaje;

public class MensajePedirListaUsuarios extends Mensaje{
    @Override
    public String getTipo() {
        return "PedirListaUsuarios";
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
