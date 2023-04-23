package Servidor;

import javafx.util.Pair;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;


public class MonitorUsers {
    Semaphore semaforo;
    private Map<String, Pair<ObjectInputStream, ObjectOutputStream>> entradaSalidaUsers;

    public MonitorUsers() {
        entradaSalidaUsers = new HashMap<>();
        semaforo = new Semaphore(1);
    }

    public void anadirUsuario(String id, ObjectInputStream entrada, ObjectOutputStream salida) throws InterruptedException {
        semaforo.acquire();
        entradaSalidaUsers.put(id, new Pair<>(entrada, salida));
        semaforo.release();
    }

    public void eliminarUsuario(String id) throws InterruptedException {
        semaforo.acquire();
        entradaSalidaUsers.remove(id);
        semaforo.release();
    }

    public Pair<ObjectInputStream, ObjectOutputStream> getUsuarioPorId(String id) throws RuntimeException, InterruptedException {
        semaforo.acquire();
        if(!entradaSalidaUsers.containsKey(id))
            throw new RuntimeException();
        else{
            Pair<ObjectInputStream, ObjectOutputStream>  usuarios = entradaSalidaUsers.get(id);
            semaforo.release();
            return usuarios;
        }

    }
}
