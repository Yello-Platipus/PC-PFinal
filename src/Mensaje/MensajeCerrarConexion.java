package Mensaje;

import java.util.Set;

public class MensajeCerrarConexion extends Mensaje{

    private String id;
    private Set<String> info;

    public MensajeCerrarConexion(String origen, String destino, String id, Set<String> info) {
        super(origen, destino,TiposMensajes.CERRAR_CONEXION);
        this.id = id;
        this.info = info;
    }


    public String getId(){
        return id;
    }

    public Set<String> getInfo(){
        return info;
    }
}
