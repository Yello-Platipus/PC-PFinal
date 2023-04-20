package GUI;

import Cliente.Cliente;
import Cliente.Usuario;
import Util.GeneradorPuertos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainWindowCliente extends JFrame {

    private JTextField nombreUsuario;
    private Cliente cliente;

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
        this.setTitle("Cliente" + c.getId());
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JLabel m = new JLabel("Bienvenido " + c.getId());
    }

    public String getNombreUsuario() {
        return nombreUsuario.getText();
    }
}
