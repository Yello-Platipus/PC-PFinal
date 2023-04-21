package Mensaje;

import java.util.Set;

public class MensajeActualizar extends Mensaje{

    private String fichero;

    public MensajeActualizar(String origen, String destino, String fichero) {
        super(origen, destino,TiposMensajes.ACTUALIZAR);
        this.fichero = fichero;
    }


    public String getFichero(){
        return fichero;
    }

}