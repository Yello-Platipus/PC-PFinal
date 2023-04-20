package Mensaje;

public class MensajeOkConexion extends Mensaje{

    public MensajeOkConexion(String origen, String destino) {
        super(origen, destino,TiposMensajes.OK_CONEXION);
    }

}
