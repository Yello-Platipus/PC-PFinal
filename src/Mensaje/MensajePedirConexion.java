package Mensaje;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

public class MensajePedirConexion extends Mensaje{
    InputStream is;
    OutputStream os;
    String id;
    Set<String> info;
    public MensajePedirConexion(String origen, String destino, String id, Set<String> info, InputStream is, OutputStream os) {
        super(origen, destino);
        this.id = id;
        this.info = info;
        this.is = is;
        this.os = os;
    }
    @Override
    public String getTipo() {
        return "PedirConexion";
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

    public InputStream getInputStream(){
        return is;
    }

    public OutputStream getOutputStream(){
        return os;
    }
}
