package Mensaje;

public class MensajeEmitirFichero extends Mensaje{
    String fichero;
    public MensajeEmitirFichero(String origen, String destino, String fichero){
        super(origen, destino,TiposMensajes.EMITIR_FICHERO);
        this.fichero = fichero;
    }

    public String getFichero(){
        return fichero;
    }

}