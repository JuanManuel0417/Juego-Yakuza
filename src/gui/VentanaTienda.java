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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaTienda extends JDialog {
    private Yakuza jugador;

    public VentanaTienda(JFrame parent, Yakuza jugador) {
        super(parent, "Tienda", true);
        this.jugador = jugador;

        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        JLabel tituloLabel = new JLabel("TIENDA", JLabel.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tituloLabel.setOpaque(true);
        tituloLabel.setBackground(new Color(50, 100, 150));
        tituloLabel.setForeground(Color.WHITE);
        add(tituloLabel, BorderLayout.NORTH);

        JLabel dineroLabel = new JLabel("Dinero disponible: $" + jugador.getDinero(), JLabel.CENTER);
        dineroLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dineroLabel.setOpaque(true);
        dineroLabel.setBackground(new Color(200, 220, 240));
        add(dineroLabel, BorderLayout.NORTH);
        add(dineroLabel, BorderLayout.NORTH);

        JPanel productosPanel = new JPanel(new GridLayout(4, 2, 8, 8));
        productosPanel.setBackground(new Color(240, 240, 240));

        agregarProducto(productosPanel, "Cuchillo");
        agregarProducto(productosPanel, "Pistola");
        agregarProducto(productosPanel, "Katana");
        agregarProducto(productosPanel, "Glock");
        agregarProducto(productosPanel, "Venda");
        agregarProducto(productosPanel, "Agua");
        agregarProducto(productosPanel, "Botiquin");

        add(productosPanel, BorderLayout.CENTER);

        JButton cerrarBtn = new JButton("Cerrar");
        cerrarBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        cerrarBtn.setBackground(new Color(200, 100, 100));
        cerrarBtn.setForeground(Color.WHITE);
        cerrarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cerrarBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void agregarProducto(JPanel panel, String nombre) {
        // Crear instancia temporal para obtener el rango de precio
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

        JButton btn = new JButton(nombre + " " + precioRango);
        btn.setFont(new Font("Arial", Font.PLAIN, 11));
        btn.setBackground(new Color(100, 150, 200));
        btn.setForeground(Color.WHITE);

        final String nombreProducto = nombre;
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comprarProducto(nombreProducto);
                ((JButton) e.getSource()).getParent().revalidate();
            }
        });

        panel.add(btn);
    }

    private void comprarProducto(String nombre) {
        int precio = 0;

        // Obtener precio aleatorio del producto
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
