package Util;

public class GeneradorPuertos {
    private static int numPuerto = 8081;

    public static synchronized int nuevoPuerto() {
        return numPuerto++;
    }
}
