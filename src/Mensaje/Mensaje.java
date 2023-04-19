package Mensaje;

import java.io.Serializable;

public abstract class Mensaje implements Serializable {
    String idOrigen;
    String idDestino;
    public Mensaje(String origen, String destino) {
        idOrigen = origen;
        idDestino = destino;
    }
    public abstract String getTipo();

    public String getOrigen() {
        return idOrigen;
    }

    public String getDestino() {
        return idDestino;
    }
}
