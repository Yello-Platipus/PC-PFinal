package Mensaje;

import java.util.ArrayList;

public class MensajeOkListaUsuarios extends Mensaje{
    ArrayList<String> listaUsuarios;
    public MensajeOkListaUsuarios(String origen, String destino, ArrayList<String> listaUsuarios) {
        super(origen, destino,TiposMensajes.OK_LISTA_USUARIOS);
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<String> getListaUsuarios(){
        return listaUsuarios;
    }
}
