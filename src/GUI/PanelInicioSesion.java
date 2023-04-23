package GUI;

import javax.swing.*;

public class PanelInicioSesion extends JPanel {
    private JTextField nombreUsuario;
    private JButton conectar;

    public PanelInicioSesion() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel m = new JLabel("Introduzca su nombre de usuario:");
        this.add(m);
        nombreUsuario = new JTextField(30);
        nombreUsuario.setMaximumSize(new java.awt.Dimension(400, 50));
        this.add(nombreUsuario);
        conectar = new JButton("Conectar");
        this.add(conectar);
    }

    public String getNombreUsuario() {
        return nombreUsuario.getText();
    }

    public JButton getBotonConectar() {
        return conectar;
    }
}
