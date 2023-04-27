package GUI;

import Cliente.Cliente;
import Cliente.Usuario;
import Util.GeneradorPuertos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainWindowCliente extends JFrame {

    private Cliente cliente;
    private ArrayList<String> ficheros;

    private PanelInicioSesion panelInicioSesion;
    private PanelClienteConectado panelCliente;

    public MainWindowCliente() {
        super();
        this.setTitle("Login Cliente");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        panelInicioSesion = new PanelInicioSesion();
        panelInicioSesion.getBotonConectar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = getNombreUsuario();
                if (id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe introducir un nombre de usuario", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    InetAddress ip = null;
                    try {
                        ip = InetAddress.getLocalHost();
                        cliente = new Cliente(new Usuario(id, ip, GeneradorPuertos.nuevoPuerto()));
                        cliente.conectar();
                        dispose();
                    } catch (UnknownHostException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.add(panelInicioSesion);

        this.setVisible(true);
    }

    public MainWindowCliente(Cliente c){
        super();
        cliente = c;
        this.setTitle("Cliente " + cliente.getId());
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        panelCliente = new PanelClienteConectado(cliente);
        this.add(panelCliente);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    cliente.cerrarSesion();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    cliente.cerrarSesion();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public MainWindowCliente(Cliente c, ArrayList<String> ficheros){
        super();
        cliente = c;
        this.ficheros = ficheros;
        this.setTitle("Cliente " + cliente.getId());
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        panelCliente = new PanelClienteConectado(cliente, ficheros);
        this.add(panelCliente);

        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    cliente.cerrarSesion();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void anadirPedirFichero(JComboBox<String> listaFicheros){
        JButton pedirFichero = new JButton("Pedir fichero");
        pedirFichero.addActionListener(new ActionListener() {
            JComboBox<String> listaFicheros;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliente.enviarPedirFichero((String) listaFicheros.getSelectedItem());
                    //dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            public ActionListener init(JComboBox<String> listaFicheros){
                this.listaFicheros = listaFicheros;
                return this;
            }
        }.init(listaFicheros));
        this.add(pedirFichero);
    }

    public String getNombreUsuario() {
        return panelInicioSesion.getNombreUsuario();
    }
}
