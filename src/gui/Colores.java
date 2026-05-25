package gui;

import java.awt.Color;
import java.awt.Font;

// Clase utilitaria static con todos los colores y fuentes del juego
// Centralizado aqui para que todas las pantallas tengan el mismo estilo
public class Colores {

    // Fondo general oscuro
    public static final Color FONDO         = new Color(13, 13, 13);
    // Fondo de paneles internos
    public static final Color PANEL         = new Color(17, 17, 17);
    // Fondo de elementos secundarios
    public static final Color PANEL2        = new Color(26, 26, 26);
    // Bordes sutiles
    public static final Color BORDE         = new Color(34, 34, 34);
    // Borde activo/hover
    public static final Color BORDE_ACTIVO  = new Color(239, 159, 39);

    // Colores de texto
    public static final Color TEXTO         = new Color(221, 221, 221);
    public static final Color TEXTO_MUTED   = new Color(100, 100, 100);
    public static final Color TEXTO_GRIS    = new Color(60, 60, 60);

    // Colores semanticos
    public static final Color AMARILLO      = new Color(239, 159, 39);
    public static final Color VERDE         = new Color(99, 153, 34);
    public static final Color VERDE_CLARO   = new Color(93, 202, 165);
    public static final Color ROJO          = new Color(163, 45, 45);
    public static final Color ROJO_CLARO    = new Color(226, 75, 74);
    public static final Color AMARILLO_BARRA= new Color(186, 117, 23);

    // Fuentes — todas monospace para el estilo terminal
    public static final Font FUENTE_LOGO    = new Font("Monospaced", Font.BOLD,  12);
    public static final Font FUENTE_TITULO  = new Font("Monospaced", Font.BOLD,  14);
    public static final Font FUENTE_NORMAL  = new Font("Monospaced", Font.PLAIN, 12);
    public static final Font FUENTE_PEQUENA = new Font("Monospaced", Font.PLAIN, 11);
    public static final Font FUENTE_GRANDE  = new Font("Monospaced", Font.BOLD,  18);
}
