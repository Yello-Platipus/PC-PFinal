package Mensaje;

import java.util.Set;

public class MensajeCerrarConexion extends Mensaje{

    private String id;
    private String[] info;

    public MensajeCerrarConexion(String origen, String destino, String id, String [] info) {
        super(origen, destino,TiposMensajes.CERRAR_CONEXION);
        this.id = id;
        this.info = info;
    }


    public String getId(){
        return id;
    }

    public String[] getInfo(){
        return info;
    }
}
