package Servidor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MonitorArchivos {
    private Map<String, ArrayList<String>> quienTiene;

    public MonitorArchivos() {
        quienTiene = new HashMap<>();
    }

    public synchronized void anadirArchivo(String id, String archivo){
        if(!quienTiene.containsKey(archivo))
            quienTiene.put(archivo, new ArrayList<>());
        quienTiene.get(archivo).add(id);
    }

    public synchronized void eliminarArchivo(String id, String archivo){
        quienTiene.get(archivo).remove(id);
    }

    public synchronized ArrayList<String> getUsuarios(String archivo){
        return quienTiene.get(archivo);
    }

    public synchronized ArrayList<String> getInfo(){
        ArrayList<String> info = new ArrayList<>();
        for(String archivo : quienTiene.keySet()){
            info.add(archivo);
        }
        return info;
    }


}
