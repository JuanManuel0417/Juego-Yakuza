package gui;

import personajes.Yakuza;
import armas.Arma;
import armas.Cuchillo;
import armas.Glock;
import armas.Katana;
import armas.Pistola;
import curas.Agua;
import curas.Botiquin;
import curas.Cura;
import curas.Venda;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaTienda extends JDialog {
    private Yakuza jugador;

    public VentanaTienda(JFrame parent, Yakuza jugador) {
        super(parent, "TIENDA", true);
        this.jugador = jugador;

        setSize(540, 420);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Colores.FONDO);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Colores.PANEL);
        header.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.BORDE),
            BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));

        JLabel tituloLabel = new JLabel("⚔  TIENDA");
        tituloLabel.setFont(Colores.FUENTE_GRANDE);
        tituloLabel.setForeground(Colores.AMARILLO);
        header.add(tituloLabel, BorderLayout.WEST);

        JLabel dineroLabel = new JLabel("$ " + jugador.getDinero());
        dineroLabel.setFont(Colores.FUENTE_TITULO);
        dineroLabel.setForeground(Colores.VERDE);
        dineroLabel.setHorizontalAlignment(JLabel.RIGHT);
        header.add(dineroLabel, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        JPanel productosPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        productosPanel.setBackground(Colores.FONDO);
        productosPanel.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        agregarProducto(productosPanel, "Cuchillo");
        agregarProducto(productosPanel, "Pistola");
        agregarProducto(productosPanel, "Katana");
        agregarProducto(productosPanel, "Glock");
        agregarProducto(productosPanel, "Venda");
        agregarProducto(productosPanel, "Agua");
        agregarProducto(productosPanel, "Botiquin");

        add(productosPanel, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setBackground(Colores.FONDO);
        footer.setBorder(BorderFactory.createEmptyBorder(0, 16, 12, 16));
        BotonYakuza cerrarBtn = new BotonYakuza("Cerrar", true);
        cerrarBtn.setPreferredSize(new Dimension(140, 44));
        cerrarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        footer.add(cerrarBtn);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void agregarProducto(JPanel panel, String nombre) {
        Arma armaTemp = null;
        Cura curaTemp = null;
        String precioRango = "";

        if (nombre.equals("Cuchillo")) {
            armaTemp = new Cuchillo();
            precioRango = "$40-60";
        } else if (nombre.equals("Pistola")) {
            armaTemp = new Pistola();
            precioRango = "$130-170";
        } else if (nombre.equals("Katana")) {
            armaTemp = new Katana();
            precioRango = "$180-220";
        } else if (nombre.equals("Glock")) {
            armaTemp = new Glock();
            precioRango = "$230-270";
        } else if (nombre.equals("Venda")) {
            curaTemp = new Venda();
            precioRango = "$20-40";
        } else if (nombre.equals("Agua")) {
            curaTemp = new Agua();
            precioRango = "$10-20";
        } else if (nombre.equals("Botiquin")) {
            curaTemp = new Botiquin();
            precioRango = "$80-120";
        }

        BotonYakuza btn = new BotonYakuza(nombre + " " + precioRango);
        btn.setPreferredSize(new Dimension(0, 48));

        final String nombreProducto = nombre;
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comprarProducto(nombreProducto);
                btn.getParent().revalidate();
            }
        });

        panel.add(btn);
    }

    private void comprarProducto(String nombre) {
        int precio = 0;

        if (nombre.equals("Cuchillo")) {
            Cuchillo temp = new Cuchillo();
            precio = temp.getPrecio();
        } else if (nombre.equals("Pistola")) {
            Pistola temp = new Pistola();
            precio = temp.getPrecio();
        } else if (nombre.equals("Katana")) {
            Katana temp = new Katana();
            precio = temp.getPrecio();
        } else if (nombre.equals("Glock")) {
            Glock temp = new Glock();
            precio = temp.getPrecio();
        } else if (nombre.equals("Venda")) {
            Venda temp = new Venda();
            precio = temp.getPrecio();
        } else if (nombre.equals("Agua")) {
            Agua temp = new Agua();
            precio = temp.getPrecio();
        } else if (nombre.equals("Botiquin")) {
            Botiquin temp = new Botiquin();
            precio = temp.getPrecio();
        }

        if (!jugador.tieneDinero(precio)) {
            JOptionPane.showMessageDialog(this, "No tienes suficiente dinero.\nPrecio: $" + precio, "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        jugador.gastarDinero(precio);

        if (nombre.equals("Cuchillo")) {
            jugador.guardarArma(new Cuchillo());
        } else if (nombre.equals("Pistola")) {
            jugador.guardarArma(new Pistola());
        } else if (nombre.equals("Katana")) {
            jugador.guardarArma(new Katana());
        } else if (nombre.equals("Glock")) {
            jugador.guardarArma(new Glock());
        } else if (nombre.equals("Venda")) {
            jugador.guardarCura(new Venda());
        } else if (nombre.equals("Agua")) {
            jugador.guardarCura(new Agua());
        } else if (nombre.equals("Botiquin")) {
            jugador.guardarCura(new Botiquin());
        }

        JOptionPane.showMessageDialog(this, "¡Compraste " + nombre + " por $" + precio + "!", "Compra exitosa", JOptionPane.INFORMATION_MESSAGE);
    }
}
