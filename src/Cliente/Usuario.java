package Cliente;

import java.io.File;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class Usuario {
    public final static String ruta = "../../data/";
    private String id;
    private InetAddress ip;
    private int puerto;
    private Set<String> info;

    public Usuario(){
        this.id = null;
        this.ip = null;
        this.puerto = 0;
        this.info = null;
    }

    public Usuario(String id, InetAddress ip, int puerto) {
        this.id = id;
        this.ip = ip;
        this.puerto = puerto;
        info = new HashSet<>();
        File carpeta = new File(ruta + id);
        if (!carpeta.exists())
            carpeta.mkdir();
        else
            for (File archivo : carpeta.listFiles())
                if (archivo.isFile())
                    info.add(archivo.getName());
    }


    // Getters
    public String getId() {
        return id;
    }
    public InetAddress getIp() {
        return ip;
    }
    public int getPuerto() {
        return puerto;
    }
    public Set<String> getInfo() {
        return info;
    }
}
