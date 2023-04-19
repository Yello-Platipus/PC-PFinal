package Mensaje;

import java.util.Set;

public class MensajeCerrarConexion extends Mensaje{

    private String id;
    private Set<String> info;

    public MensajeCerrarConexion(String origen, String destino, String id, Set<String> info) {
        super(origen, destino);
        this.id = id;
        this.info = info;
    }
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

    public String getId(){
        return id;
    }

    public Set<String> getInfo(){
        return info;
    }
}
