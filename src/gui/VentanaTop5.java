package gui;

import utils.Top5Manager;
import utils.TopScore;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaTop5 extends JDialog {
    private JTextArea areaTop5;

    public VentanaTop5(JFrame parent) {
        super(parent, "TOP 5", true);
        setSize(460, 380);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Colores.FONDO);

        JLabel titulo = new JLabel("🏆  TOP 5 — MEJORES PUNTAJES", JLabel.CENTER);
        titulo.setFont(Colores.FUENTE_GRANDE);
        titulo.setOpaque(true);
        titulo.setBackground(Colores.PANEL);
        titulo.setForeground(Colores.AMARILLO);
        titulo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.BORDE),
            BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));
        add(titulo, BorderLayout.NORTH);

        areaTop5 = new JTextArea();
        areaTop5.setEditable(false);
        areaTop5.setFont(Colores.FUENTE_PEQUENA);
        areaTop5.setBackground(new Color(10, 10, 10));
        areaTop5.setForeground(Colores.TEXTO_MUTED);
        areaTop5.setLineWrap(false);
        areaTop5.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Colores.BORDE, 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        JScrollPane scroll = new JScrollPane(areaTop5);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(10, 10, 10));
        add(scroll, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setBackground(Colores.FONDO);
        footer.setBorder(BorderFactory.createEmptyBorder(0, 16, 12, 16));
        BotonYakuza cerrarBtn = new BotonYakuza("Cerrar", true);
        cerrarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        footer.add(cerrarBtn);
        add(footer, BorderLayout.SOUTH);

        actualizarTop5();
        setVisible(true);
    }

    private void actualizarTop5() {
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
    }
}
