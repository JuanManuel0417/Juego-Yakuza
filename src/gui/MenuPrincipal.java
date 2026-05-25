package gui;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        setTitle("Yakuza Battle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(540, 480);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Colores.FONDO);

        JPanel centro = new JPanel();
        centro.setBackground(Colores.FONDO);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Logo tipo ASCII art de bloques sólidos, optimizado para medir 540px de ancho sin deformarse
        String asciiLogo =
            "██   ██  █████  ██   ██ ██    ██ ███████  █████  \n" +
            " ██ ██  ██   ██ ██  ██  ██    ██      ██ ██   ██ \n" +
            "  ███   ███████ █████   ██    ██    ███  ███████ \n" +
            "  ██    ██   ██ ██  ██  ██    ██   ██    ██   ██ \n" +
            "  ██    ██   ██ ██   ██  ██████  ███████ ██   ██ ";

        JTextArea logoArea = new JTextArea(asciiLogo);
        logoArea.setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 14));
        logoArea.setForeground(Colores.AMARILLO);
        logoArea.setBackground(Colores.FONDO);
        logoArea.setEditable(false);
        logoArea.setFocusable(false);
        logoArea.setOpaque(false);
        logoArea.setHighlighter(null);
        logoArea.setAlignmentX(CENTER_ALIGNMENT);
        logoArea.setBorder(null);
        centro.add(logoArea);

        centro.add(Box.createVerticalStrut(8));

        // Subtítulo
        JLabel sub = new JLabel("⚔   Conviértete en el líder de la organización   ⚔");
        sub.setFont(Colores.FUENTE_PEQUENA);
        sub.setForeground(Colores.TEXTO_MUTED);
        sub.setAlignmentX(CENTER_ALIGNMENT);
        centro.add(sub);

        centro.add(Box.createVerticalStrut(24));

        // Separador
        JSeparator sep1 = new JSeparator();
        sep1.setForeground(Colores.BORDE);
        sep1.setMaximumSize(new java.awt.Dimension(300, 1));
        centro.add(sep1);
        centro.add(Box.createVerticalStrut(18));

        // Botones
        BotonYakuza iniciarBtn = new BotonYakuza("▶   NUEVA PARTIDA", true);
        iniciarBtn.setAlignmentX(CENTER_ALIGNMENT);
        iniciarBtn.setMaximumSize(new java.awt.Dimension(300, 44));
        centro.add(iniciarBtn);
        centro.add(Box.createVerticalStrut(10));

        BotonYakuza top5Btn = new BotonYakuza("🏆   VER TOP 5");
        top5Btn.setAlignmentX(CENTER_ALIGNMENT);
        top5Btn.setMaximumSize(new java.awt.Dimension(300, 44));
        centro.add(top5Btn);
        centro.add(Box.createVerticalStrut(10));

        BotonYakuza instruccionesBtn = new BotonYakuza("⚙   INSTRUCCIONES");
        instruccionesBtn.setAlignmentX(CENTER_ALIGNMENT);
        instruccionesBtn.setMaximumSize(new java.awt.Dimension(300, 44));
        centro.add(instruccionesBtn);

        centro.add(Box.createVerticalStrut(18));
        JSeparator sep2 = new JSeparator();
        sep2.setForeground(Colores.BORDE);
        sep2.setMaximumSize(new java.awt.Dimension(300, 1));
        centro.add(sep2);

        // Pie de página
        JLabel pie = new JLabel("Yakuza RPG — POO Java — Pascual Bravo");
        pie.setFont(Colores.FUENTE_PEQUENA);
        pie.setForeground(Colores.TEXTO_GRIS);
        pie.setAlignmentX(CENTER_ALIGNMENT);
        centro.add(Box.createVerticalStrut(10));
        centro.add(pie);

        // Añadir panel central al frame usando GridBagLayout de forma correcta
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(centro, gbc);

        // Acciones
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
        instruccionesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                javax.swing.JOptionPane.showMessageDialog(MenuPrincipal.this,
                        "1. Ingresa tu nombre al iniciar.\n" +
                        "2. Compra armas y curas en la tienda.\n" +
                        "3. En cada turno elige: Atacar, Habilidad, Usar cura, Cambiar arma.\n" +
                        "4. ¡Vence rápido para sumar más puntos!",
                        "INSTRUCCIONES", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        });

        setVisible(true);
    }
}