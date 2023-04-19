package Mensaje;

public class MensajeDevolverListaUsuarios extends Mensaje{

    public MensajeDevolverListaUsuarios(String origen, String destino) {
        super(origen, destino);

    }
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
