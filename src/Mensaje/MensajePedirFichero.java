package Mensaje;

public class MensajePedirFichero extends Mensaje{
    private String fichero;
    public MensajePedirFichero(String origen, String destino,String fichero) {
        super(origen, destino);
        this.fichero = fichero;
    }
    @Override
    public String getTipo() {
        return "PedirFichero";
    }

    @Override
    public String getOrigen() {
        return null;
    }

    @Override
    public String getDestino() {
        return null;
    }

    public String getFichero(){
        return fichero;
    }
}