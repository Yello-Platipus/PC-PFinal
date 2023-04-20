package Mensaje;

public class MensajePedirFichero extends Mensaje{
    String fichero;
    public MensajePedirFichero(String origen, String destino,String fichero) {
        super(origen, destino,TiposMensajes.PEDIR_FICHERO);
        this.fichero = fichero;
    }


    public String getFichero(){
        return fichero;
    }
}