package gui;

import utils.Top5Manager;
import utils.TopScore;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaTop5 extends JDialog {
    public VentanaTop5(JFrame parent) {
        super(parent, "Top 5", true);
        setSize(420, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        JLabel titulo = new JLabel("Top 5 Mejores Puntajes", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(150, 150, 100));
        titulo.setForeground(Color.WHITE);
        add(titulo, BorderLayout.NORTH);

        JTextArea areaTop5 = new JTextArea();
        areaTop5.setEditable(false);
        areaTop5.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaTop5.setBackground(new Color(245, 245, 245));
        areaTop5.setLineWrap(false);
        add(new JScrollPane(areaTop5), BorderLayout.CENTER);

        JButton cerrarBtn = new JButton("Cerrar");
        cerrarBtn.setFont(new Font("Arial", Font.BOLD, 12));
        cerrarBtn.setBackground(new Color(100, 150, 100));
        cerrarBtn.setForeground(Color.WHITE);
        cerrarBtn.setFocusPainted(false);
        add(cerrarBtn, BorderLayout.SOUTH);
        cerrarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        Top5Manager manager = new Top5Manager();
        ArrayList<TopScore> lista = manager.cargarTop5();
        if (lista.isEmpty()) {
            areaTop5.setText("\n\nNo hay puntajes guardados aún.\nCompleta algunos juegos para aparecer aquí.");
        } else {
            String texto = "\n  POSICIÓN    JUGADOR    NIVEL    PUNTOS\n";
            texto += "  =========================================\n";
            for (int i = 0; i < lista.size(); i++) {
                TopScore item = lista.get(i);
                texto += String.format("     %d        %-15s %2d      %d\n", (i + 1), item.getNombre(), item.getNivel(), item.getPuntaje());
            }
            areaTop5.setText(texto);
        }

        setVisible(true);
    }
}
