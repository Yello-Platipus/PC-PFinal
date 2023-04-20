package Mensaje;

import java.io.Serializable;

public abstract class Mensaje implements Serializable {
    String idOrigen;
    String idDestino;
    TiposMensajes tipo;
    public Mensaje(String origen, String destino,TiposMensajes tipoM) {
        idOrigen = origen;
        idDestino = destino;
        tipo = tipoM;
    }
    public TiposMensajes getTipo(){
        return tipo;
    }

    public String getOrigen() {
        return idOrigen;
    }

    public String getDestino() {
        return idDestino;
    }
}
