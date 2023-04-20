package Launchers;

import Servidor.Servidor;

import java.io.IOException;

public class LauncherServidor {
    public static void main(String[] args) {
        try {
            new Servidor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}