package Mensaje;

public class MensajePedirListaUsuarios extends Mensaje{

    public MensajePedirListaUsuarios(String origen, String destino) {
        super(origen, destino,TiposMensajes.LISTA_USUARIOS);
    }

}
