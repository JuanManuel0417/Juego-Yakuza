package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        setTitle("Juego Yakuza - Menú");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(30, 30, 40));

        JLabel titulo = new JLabel("Yakuza Battle", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(255, 100, 100));
        titulo.setOpaque(true);
        titulo.setBackground(new Color(50, 50, 60));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 15, 15));
        panelBotones.setBackground(new Color(30, 30, 40));
        panelBotones.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JButton iniciarBtn = new JButton("Iniciar Juego");
        iniciarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        iniciarBtn.setBackground(new Color(100, 150, 200));
        iniciarBtn.setForeground(Color.WHITE);
        iniciarBtn.setFocusPainted(false);

        JButton top5Btn = new JButton("Top 5");
        top5Btn.setFont(new Font("Arial", Font.BOLD, 16));
        top5Btn.setBackground(new Color(150, 150, 100));
        top5Btn.setForeground(Color.WHITE);
        top5Btn.setFocusPainted(false);

        JButton salirBtn = new JButton("Salir");
        salirBtn.setFont(new Font("Arial", Font.BOLD, 16));
        salirBtn.setBackground(new Color(200, 100, 100));
        salirBtn.setForeground(Color.WHITE);
        salirBtn.setFocusPainted(false);

        panelBotones.add(iniciarBtn);
        panelBotones.add(top5Btn);
        panelBotones.add(salirBtn);

        add(panelBotones, BorderLayout.CENTER);

        iniciarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaJuego(MenuPrincipal.this);
                setVisible(false);
            }
        });

        top5Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaTop5(MenuPrincipal.this);
            }
        });

        salirBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
}
