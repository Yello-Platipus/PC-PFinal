package Servidor;

import javafx.util.Pair;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


public class MonitorUsers {
    private Map<String, Pair<ObjectInputStream, ObjectOutputStream>> entradaSalidaUsers;

    public MonitorUsers() {
        entradaSalidaUsers = new HashMap<>();

    }

    public synchronized void anadirUsuario(String id, ObjectInputStream entrada, ObjectOutputStream salida){
        entradaSalidaUsers.put(id, new Pair<>(entrada, salida));
    }

    public synchronized void eliminarUsuario(String id){
        entradaSalidaUsers.remove(id);
    }

    public synchronized Pair<ObjectInputStream, ObjectOutputStream>  getUsuarios(String id){
        return entradaSalidaUsers.get(id);
    }

    public synchronized Pair<ObjectInputStream, ObjectOutputStream> getUsuarioPorId(String id) throws RuntimeException{
        if(!entradaSalidaUsers.containsKey(id))
            throw new RuntimeException();
        else
            return entradaSalidaUsers.get(id);
    }
}
