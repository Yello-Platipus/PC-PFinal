package GUI;

import Cliente.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                    cliente = new Cliente(new Usuario(id));
                }
            }
        });
        this.add(conectar);
    }

    public String getNombreUsuario() {
        return nombreUsuario.getText();
    }
}
