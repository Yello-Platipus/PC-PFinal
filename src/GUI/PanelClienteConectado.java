package GUI;

import Cliente.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class PanelClienteConectado extends JPanel {

    private Cliente cliente;

    public PanelClienteConectado(Cliente c){
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        cliente = c;

        JLabel m = new JLabel("Bienvenido " + cliente.getId());
        this.add(m);

        JButton pedirListaFicheros = new JButton("Pedir lista de ficheros disponibles");
        pedirListaFicheros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliente.enviarPedirLista();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.add(pedirListaFicheros);
    }

    public PanelClienteConectado(Cliente c, ArrayList<String> ficheros){
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        cliente = c;

        JComboBox<String> listaFicheros = new JComboBox<>();

        JLabel m = new JLabel("Bienvenido " + cliente.getId());
        this.add(m);

        JButton pedirListaFicheros = new JButton("Pedir lista de ficheros disponibles");
        pedirListaFicheros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cliente.enviarPedirLista();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.add(pedirListaFicheros);

        for(String fichero : ficheros)
            listaFicheros.addItem(fichero);
        this.add(listaFicheros);
        anadirPedirFichero(listaFicheros);
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

}
