package Mensaje;

public class MensajePedirListaUsuarios extends Mensaje{

    public MensajePedirListaUsuarios(String origen, String destino) {
        super(origen, destino);
    }
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
