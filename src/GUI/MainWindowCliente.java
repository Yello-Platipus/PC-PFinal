package GUI;

import Cliente.Cliente;
import Cliente.Usuario;
import Util.GeneradorPuertos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainWindowCliente extends JPanel {

    private JTextField nombreUsuario;
    private Cliente cliente;

    public MainWindowCliente() {
        super();
        this.add(new JLabel("Introduzca su nombre de usuario:"));
        nombreUsuario = new JTextField(30);
        this.add(nombreUsuario);
        JButton conectar = new JButton("Conectar");
        conectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = getNombreUsuario();
                if (id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe introducir un nombre de usuario");
                } else {
                    InetAddress ip = null;
                    try {
                        ip = InetAddress.getLocalHost();
                        cliente = new Cliente(new Usuario(id, ip, GeneradorPuertos.nuevoPuerto()));
                    } catch (UnknownHostException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.add(conectar);
    }

    public String getNombreUsuario() {
        return nombreUsuario.getText();
    }
}
