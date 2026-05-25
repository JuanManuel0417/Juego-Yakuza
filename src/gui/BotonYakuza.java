package gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Boton personalizado reutilizable con el estilo oscuro del juego
// Extiende JButton (herencia) para mantener todos sus comportamientos
public class BotonYakuza extends JButton {

    private boolean esPrimario;

    public BotonYakuza(String texto, boolean esPrimario){
        super(texto);
        this.esPrimario = esPrimario;
        aplicarEstilo();
        agregarHover();
    }

    public BotonYakuza(String texto){
        this(texto, false);
    }

    private void aplicarEstilo(){
        setFont(Colores.FUENTE_TITULO);
        setBackground(Colores.PANEL2);
        setForeground(esPrimario ? Colores.AMARILLO : Colores.TEXTO);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(esPrimario ? Colores.BORDE_ACTIVO : Colores.BORDE, 1),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        setFocusPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setOpaque(true);
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void agregarHover(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                setBackground(new Color(30, 30, 30));
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Colores.AMARILLO, 1),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
                ));
                setForeground(Colores.AMARILLO);
            }
            @Override
            public void mouseExited(MouseEvent e){
                aplicarEstilo();
            }
        });
    }
}
