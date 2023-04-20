package Mensaje;

public class MensajeEmitirFichero extends Mensaje{
    String deDonde;
    public MensajeEmitirFichero(String origen, String destino){
        super(origen, destino,TiposMensajes.EMITIR_FICHERO);
        deDonde = origen;
    }

    public String getDeDonde(){
        return deDonde;
    }

}