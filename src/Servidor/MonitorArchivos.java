package Servidor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class MonitorArchivos {
    private Map<String, ArrayList<String>> quienTiene;
    Semaphore semaforo;
    public MonitorArchivos() {
        quienTiene = new HashMap<>();
        semaforo = new Semaphore(1);
    }

    public void anadirArchivo(String id, String archivo) throws InterruptedException {
        semaforo.acquire();
        if(!quienTiene.containsKey(archivo))
            quienTiene.put(archivo, new ArrayList<>());
        quienTiene.get(archivo).add(id);
        semaforo.release();
    }

    public void eliminarArchivo(String id, String archivo) throws InterruptedException {
        semaforo.acquire();
        quienTiene.get(archivo).remove(id);
        if(quienTiene.get(archivo).isEmpty())
            quienTiene.remove(archivo);
        semaforo.release();
    }

    public ArrayList<String> getUsuarios(String archivo) throws InterruptedException {
        semaforo.acquire();
        ArrayList<String> usuarios = quienTiene.get(archivo);
        semaforo.release();
        return usuarios;
    }


    public ArrayList<String> getInfo() throws InterruptedException {
        semaforo.acquire();
        ArrayList<String> info = new ArrayList<>();
        for(String archivo : quienTiene.keySet()){
            info.add(archivo);
        }
        semaforo.release();
        return info;
    }


}
