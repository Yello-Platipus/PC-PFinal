package Mensaje;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

public class MensajePedirConexion extends Mensaje{
    String id;
    Set<String> info;
    public MensajePedirConexion(String origen, String destino, String id, Set<String> info) {
        super(origen, destino,TiposMensajes.CONEXION);
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
