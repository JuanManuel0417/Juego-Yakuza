package gui;

import juego.Juego;
import personajes.Personaje;
import personajes.Policia;
import personajes.Yakuza;
import utils.Top5Manager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

public class VentanaJuego extends JFrame {
    private MenuPrincipal menu;
    private Juego juego;
    private Yakuza jugador;
    private ArrayList<Personaje> enemigos;
    private Personaje enemigoActual;
    private int nivelActual;
    private Random random;
    private Top5Manager top5Manager;

    private JLabel vidaJugadorLabel;
    private JLabel vidaEnemigoLabel;
    private JLabel dineroLabel;
    private JLabel nivelLabel;
    private JLabel armaLabel;
    private JLabel curasLabel;
    private JTextArea areaMensajes;
    private JButton atacarBtn;
    private JButton curarBtn;
    private JButton esquivarBtn;
    private JButton cambiarArmaBtn;
    private JButton tiendaBtn;
    private JButton volverBtn;

    public VentanaJuego(MenuPrincipal menu) {
        this.menu = menu;
        this.juego = new Juego();
        this.random = new Random();
        this.top5Manager = new Top5Manager();

        setTitle("Yakuza - Batalla");
        setSize(620, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 240, 240));

        initComponents();
        iniciarJuego();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                menu.setVisible(true);
            }
        });

        setVisible(true);
    }

    private void initComponents() {
        vidaJugadorLabel = new JLabel("Vida: 100/100");
        vidaEnemigoLabel = new JLabel("Enemigo: 100/100");
        dineroLabel = new JLabel("Dinero: $0");
        nivelLabel = new JLabel("Nivel: 1");
        armaLabel = new JLabel("Arma: Sin arma");
        curasLabel = new JLabel("Curas: ninguna");

        vidaJugadorLabel.setFont(new Font("Arial", Font.BOLD, 12));
        vidaEnemigoLabel.setFont(new Font("Arial", Font.BOLD, 12));
        dineroLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nivelLabel.setFont(new Font("Arial", Font.BOLD, 12));
        armaLabel.setFont(new Font("Arial", Font.BOLD, 12));
        curasLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        infoPanel.setBackground(new Color(220, 220, 230));
        infoPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        infoPanel.add(vidaJugadorLabel);
        infoPanel.add(vidaEnemigoLabel);
        infoPanel.add(dineroLabel);
        infoPanel.add(nivelLabel);
        infoPanel.add(armaLabel);
        infoPanel.add(curasLabel);
        add(infoPanel, BorderLayout.NORTH);

        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        areaMensajes.setFont(new Font("Monospaced", Font.PLAIN, 11));
        areaMensajes.setBackground(new Color(245, 245, 245));
        areaMensajes.setLineWrap(true);
        areaMensajes.setWrapStyleWord(true);
        add(new JScrollPane(areaMensajes), BorderLayout.CENTER);

        atacarBtn = new JButton("Atacar");
        curarBtn = new JButton("Curarse");
        esquivarBtn = new JButton("Esquivar");
        cambiarArmaBtn = new JButton("Cambiar arma");
        tiendaBtn = new JButton("Tienda");
        volverBtn = new JButton("Volver al menú");
        volverBtn.setEnabled(false);

        atacarBtn.setBackground(new Color(200, 100, 100));
        curarBtn.setBackground(new Color(100, 150, 100));
        esquivarBtn.setBackground(new Color(100, 150, 200));
        cambiarArmaBtn.setBackground(new Color(150, 150, 100));
        tiendaBtn.setBackground(new Color(150, 100, 150));
        volverBtn.setBackground(new Color(150, 150, 150));

        atacarBtn.setForeground(Color.WHITE);
        curarBtn.setForeground(Color.WHITE);
        esquivarBtn.setForeground(Color.WHITE);
        cambiarArmaBtn.setForeground(Color.WHITE);
        tiendaBtn.setForeground(Color.WHITE);
        volverBtn.setForeground(Color.WHITE);

        for (JButton btn : new JButton[]{atacarBtn, curarBtn, esquivarBtn, cambiarArmaBtn, tiendaBtn, volverBtn}) {
            btn.setFont(new Font("Arial", Font.BOLD, 11));
            btn.setFocusPainted(false);
        }

        JPanel accionesPanel = new JPanel(new GridLayout(2, 3, 8, 8));
        accionesPanel.setBackground(new Color(240, 240, 240));
        accionesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        accionesPanel.add(atacarBtn);
        accionesPanel.add(curarBtn);
        accionesPanel.add(esquivarBtn);
        accionesPanel.add(cambiarArmaBtn);
        accionesPanel.add(tiendaBtn);
        accionesPanel.add(volverBtn);
        add(accionesPanel, BorderLayout.SOUTH);

        atacarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ataqueJugador();
            }
        });
        curarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                usarCuraJugador();
            }
        });
        esquivarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                esquivarJugador();
            }
        });
        cambiarArmaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarArmaJugador();
            }
        });
        tiendaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTienda();
            }
        });
        volverBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                menu.setVisible(true);
            }
        });
    }

    private void iniciarJuego() {
        String nombre = JOptionPane.showInputDialog(this, "Ingresa tu nombre:", "Jugador", JOptionPane.PLAIN_MESSAGE);
        if (nombre == null || nombre.trim().isEmpty()) {
            nombre = "Akira";
        }

        jugador = juego.crearJugadorAleatorio(nombre);
        enemigos = new ArrayList<Personaje>();
        enemigos.add(juego.crearEnemigoAleatorio(1));
        enemigos.add(juego.crearEnemigoAleatorio(2));
        enemigos.add(juego.crearEnemigoAleatorio(3));
        nivelActual = 0;
        enemigoActual = enemigos.get(nivelActual);

        appendMensaje(">>> Bienvenido " + jugador.getNombre() + ".");
        appendMensaje(">>> El combate comienza contra " + enemigoActual.getNombre() + ".");
        appendMensaje("");
        actualizarEstado();
    }

    private void actualizarEstado() {
        vidaJugadorLabel.setText("Vida: " + jugador.getVida() + "/" + jugador.getVidaMaxima());
        vidaEnemigoLabel.setText("Enemigo: " + enemigoActual.getVida() + "/" + enemigoActual.getVidaMaxima());
        dineroLabel.setText("Dinero: $" + jugador.getDinero());
        nivelLabel.setText("Nivel: " + jugador.getNivel());
        armaLabel.setText("Arma: " + (jugador.getArmaEquipada() != null ? jugador.getArmaEquipada().getNombre() : "Sin arma"));
        curasLabel.setText("Curas: " + obtenerTextoCuras());
    }

    private String obtenerTextoCuras() {
        if (jugador.getCantidadCuras() == 0) {
            return "ninguna";
        }
        return jugador.getCantidadCuras() + " disponible(s)";
    }

    private void abrirTienda() {
        new VentanaTienda(this, jugador);
        actualizarEstado();
    }

    private void ataqueJugador() {
        if (!jugador.estaVivo() || !enemigoActual.estaVivo()) {
            return;
        }
        int danio = jugador.atacarSilencioso(enemigoActual);
        appendMensaje(">> " + jugador.getNombre() + " atacó causando " + danio + " de daño.");
        if (!enemigoActual.estaVivo()) {
            victoriaCombate();
        } else {
            turnoEnemigo(true);
        }
        actualizarEstado();
    }

    private void usarCuraJugador() {
        if (jugador.getCantidadCuras() == 0) {
            appendMensaje("! No tienes curas disponibles.");
            return;
        }

        String[] opciones = new String[jugador.getCantidadCuras()];
        for (int i = 0; i < jugador.getCantidadCuras(); i++) {
            opciones[i] = (i + 1) + ". " + jugador.getInventarioCuras().get(i).getNombre();
        }

        String opcion = (String) JOptionPane.showInputDialog(this, "Elige una cura:", "Curar", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        if (opcion == null) {
            return;
        }

        int indice = Integer.parseInt(opcion.split("\\.")[0]) - 1;
        int vidaCurada = jugador.usarCuraSilencioso(indice);
        if (vidaCurada > 0) {
            appendMensaje(">> " + jugador.getNombre() + " se curó y recuperó " + vidaCurada + " puntos.");
            turnoEnemigo(true);
        } else {
            appendMensaje("! No se pudo usar la cura.");
        }
        actualizarEstado();
    }

    private void esquivarJugador() {
        boolean esquivo = jugador.esquivarSilencioso();
        if (!enemigoActual.estaVivo()) {
            return;
        }

        if (esquivo) {
            appendMensaje(">> " + jugador.getNombre() + " intenta esquivar.");
            turnoEnemigo(false);
        } else {
            appendMensaje(">> " + jugador.getNombre() + " no logró esquivar.");
            turnoEnemigo(true);
        }
        actualizarEstado();
    }

    private void cambiarArmaJugador() {
        if (jugador.getCantidadArmas() == 0) {
            appendMensaje("! No tienes armas para cambiar.");
            return;
        }

        String[] opciones = new String[jugador.getCantidadArmas()];
        for (int i = 0; i < jugador.getCantidadArmas(); i++) {
            opciones[i] = (i + 1) + ". " + jugador.getInventarioArmas().get(i).getNombre();
        }

        String opcion = (String) JOptionPane.showInputDialog(this, "Elige un arma:", "Cambiar arma", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        if (opcion == null) {
            return;
        }

        int indice = Integer.parseInt(opcion.split("\\.")[0]) - 1;
        jugador.cambiarArma(indice);
        appendMensaje(">> " + jugador.getNombre() + " cambió a " + jugador.getArmaEquipada().getNombre() + ".");
        actualizarEstado();
    }

    private void turnoEnemigo(boolean puedeAtacar) {
        if (!enemigoActual.estaVivo() || !jugador.estaVivo()) {
            return;
        }

        int accion = random.nextInt(3);
        if (accion == 0 && enemigoActual.getCantidadCuras() > 0) {
            int curacion = enemigoActual.usarCuraSilencioso(0);
            appendMensaje(">> " + enemigoActual.getNombre() + " se curó y recuperó " + curacion + " puntos.");
        } else {
            if (!puedeAtacar) {
                appendMensaje(">> " + enemigoActual.getNombre() + " intentó atacar pero falló.");
            } else {
                int danio = enemigoActual.atacarSilencioso(jugador);
                appendMensaje(">> " + enemigoActual.getNombre() + " atacó causando " + danio + " de daño.");
            }
        }

        if (!jugador.estaVivo()) {
            finJuego(false);
        }
        actualizarEstado();
    }

    private void victoriaCombate() {
        appendMensaje("\\n*** ¡VICTORIA! ***");
        appendMensaje("Has derrotado a " + enemigoActual.getNombre() + ".");
        if (nivelActual < enemigos.size() - 1) {
            int recompensa = 100 + random.nextInt(151);
            jugador.ganarDinero(recompensa);
            jugador.subirNivel();
            jugador.restaurarVida();
            jugador.reiniciarEsquives();
            nivelActual++;
            enemigoActual = enemigos.get(nivelActual);
            appendMensaje("Subes de nivel y recibes $" + recompensa + ".");
            appendMensaje("Nuevo rival: " + enemigoActual.getNombre());
            appendMensaje("");
            
            // Alerta de subida de nivel
            JOptionPane.showMessageDialog(this, "¡Subiste al Nivel " + jugador.getNivel() + "!\n\nRecompensa: $" + recompensa + "\nVida restaurada", "¡Nivel Subido!", JOptionPane.INFORMATION_MESSAGE);
            
            actualizarEstado();
        } else {
            finJuego(true);
        }
    }

    private void finJuego(boolean victoria) {
        String mensajeFinal = victoria ? "\\n*** ¡¡VICTORIA FINAL!! ***" : "\\n*** HAS SIDO DERROTADO ***";
        int puntaje = juego.calcularPuntaje(jugador);
        mensajeFinal += "\\nNivel alcanzado: " + jugador.getNivel();
        mensajeFinal += "\\nDinero: $" + jugador.getDinero();
        mensajeFinal += "\\nPuntaje: " + puntaje;
        
        if (!victoria) {
            // Opción de reintentar nivel si pierdes
            int opcion = JOptionPane.showConfirmDialog(this, "Fuiste derrotado en el Nivel " + jugador.getNivel() + "\n\n¿Deseas reintentar este nivel?\n(Se reiniciarán tus stats)", "¿Reintentar?", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                jugador.restaurarVida();
                jugador.reiniciarEsquives();
                enemigoActual = enemigos.get(nivelActual);
                areaMensajes.setText("");
                appendMensaje(">>> Reintentas el combate...");
                appendMensaje(">>> Rival: " + enemigoActual.getNombre());
                appendMensaje("");
                actualizarEstado();
                return;
            }
        }
        
        JOptionPane.showMessageDialog(this, mensajeFinal, "Fin del juego", JOptionPane.INFORMATION_MESSAGE);
        top5Manager.guardarPuntaje(jugador.getNombre(), jugador.getNivel(), puntaje);
        atacarBtn.setEnabled(false);
        curarBtn.setEnabled(false);
        esquivarBtn.setEnabled(false);
        cambiarArmaBtn.setEnabled(false);
        tiendaBtn.setEnabled(false);
        volverBtn.setEnabled(true);
        appendMensaje(mensajeFinal);
    }

    private void appendMensaje(String mensaje) {
        areaMensajes.append(mensaje + "\n");
        areaMensajes.setCaretPosition(areaMensajes.getDocument().getLength());
    }
}
