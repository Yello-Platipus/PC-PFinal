package Servidor;

import Mensaje.TiposMensajes;
import Oyentes.OyenteCliente;
import javafx.util.Pair;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Servidor {
    private ServerSocket serverSocket;
    private static final int puerto = 8080;
    public static final String Host = "localhost";

    private MonitorUsers monitorUsers;
    private MonitorArchivos monitorArchivos;
    public Servidor() throws IOException {
        try {
            this.serverSocket = new ServerSocket(puerto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        monitorUsers = new MonitorUsers();
        monitorArchivos = new MonitorArchivos();
        start(puerto);
    }

    private void start(int puerto) throws IOException {
        Socket cliente;
        while(true){
            cliente = serverSocket.accept();
            new OyenteCliente(cliente, this).start();

        }
    }
    /*public void end() throws IOException {
        for(Pair<InputStream, OutputStream> entradaSalida : entradaSalidaUsers.values()){
            ObjectOutputStream objetoOut = new ObjectOutputStream(entradaSalida.getValue());
            objetoOut.writeObject(TiposMensajes.CERRAR_CONEXION);
        }
        serverSocket.close();
    }*/

    public void anadirUsuario(String id, ObjectInputStream entrada, ObjectOutputStream salida, Set<String> elems) throws InterruptedException {
        monitorUsers.anadirUsuario(id, entrada, salida);
        for(String archivo : elems)
            monitorArchivos.anadirArchivo(id, archivo);
    }

    public void anadirInfo(String Id, String fichero)throws InterruptedException{
        monitorArchivos.anadirArchivo(Id, fichero);
    }

    public void eliminarUsuario(String id,String[] elems) throws InterruptedException {
        monitorUsers.eliminarUsuario(id);
        for(String archivo : elems)
            monitorArchivos.eliminarArchivo(id, archivo);
    }

    public ArrayList<String> getInfo() throws InterruptedException {
        return monitorArchivos.getInfo();
    }


    public Pair<String,Pair<ObjectInputStream, ObjectOutputStream>> getUsuario(String fichero) throws RuntimeException, InterruptedException {//TODO cuidado
        ArrayList<String> idUsuarios = monitorArchivos.getUsuarios(fichero);
        if(idUsuarios.size() == 0)
            throw new RuntimeException("No hay usuarios que tengan el archivo");
        else
            return (new Pair<>(idUsuarios.get(0), getUsuarioPorId(idUsuarios.get(0))));
    }

    public Pair<ObjectInputStream, ObjectOutputStream> getUsuarioPorId(String id) throws RuntimeException{
        try{
            return monitorUsers.getUsuarioPorId(id);
        }
        catch (Exception e){
            throw new RuntimeException("No hay usuarios con ese id");
        }
    }

    public static int getPuerto() {
        return puerto;
    }
//a
}
