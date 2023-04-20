package Mensaje;

public class MensajeEmitirFichero extends Mensaje{
    String fichero;
    String idReceptor;
    public MensajeEmitirFichero(String origen, String destino, String fichero,String idReceptor){
        super(origen, destino,TiposMensajes.EMITIR_FICHERO);
        this.fichero = fichero;
        this.idReceptor = idReceptor;
    }

    public String getFichero(){
        return fichero;
    }

    public String getIdReceptor(){
        return idReceptor;
    }

}