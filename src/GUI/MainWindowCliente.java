package GUI;

import Cliente.Cliente;
import Cliente.Usuario;
import Util.GeneradorPuertos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainWindowCliente extends JFrame {

    private JTextField nombreUsuario;
    private Cliente cliente;
    private ArrayList<String> ficheros;

    public MainWindowCliente() {
        super();
        this.setTitle("Login Cliente");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JLabel m = new JLabel("Introduzca su nombre de usuario:");
        this.add(m);
        nombreUsuario = new JTextField(30);
        nombreUsuario.setMaximumSize(new java.awt.Dimension(400, 50));
        this.add(nombreUsuario);
        JButton conectar = new JButton("Conectar");
        conectar.addActionListener(new ActionListener() {
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
        this.add(conectar);

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

        JLabel m = new JLabel("Bienvenido " + cliente.getId());
        this.add(m);

        JComboBox<String> listaFicheros;

        JButton pedirListaFicheros = new JButton("Pedir lista de ficheros disponibles");
        pedirListaFicheros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliente.enviarPedirLista();
                    dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.add(pedirListaFicheros);

        this.setVisible(true);
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

        JLabel m = new JLabel("Bienvenido " + cliente.getId());
        this.add(m);

        JComboBox<String> listaFicheros = new JComboBox<>();

        JButton pedirListaFicheros = new JButton("Pedir lista de ficheros disponibles");
        pedirListaFicheros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliente.enviarPedirLista();
                    dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.add(pedirListaFicheros);

        for(String fichero : this.ficheros)
            listaFicheros.addItem(fichero);
        this.add(listaFicheros);
        anadirPedirFichero(listaFicheros);
        this.setVisible(true);
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
        return nombreUsuario.getText();
    }
}
