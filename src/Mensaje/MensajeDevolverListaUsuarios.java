package Mensaje;

import java.util.ArrayList;

public class MensajeDevolverListaUsuarios extends Mensaje{
    ArrayList<String> listaUsuarios;
    public MensajeDevolverListaUsuarios(String origen, String destino, ArrayList<String> listaUsuarios) {
        super(origen, destino);
        this.listaUsuarios = listaUsuarios;
    }
    @Override
    public String getTipo() {
        return "DevolverListaUsuarios";
    }

    @Override
    public String getOrigen() {
        return null;
    }

    @Override
    public String getDestino() {
        return null;
    }

    public ArrayList<String> getListaUsuarios(){
        return listaUsuarios;
    }
}
