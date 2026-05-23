package juego;

import gui.MenuPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args){
        if(args.length > 0 && args[0].equalsIgnoreCase("console")){
            Juego juego = new Juego();
            juego.iniciar();
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new MenuPrincipal();
                }
            });
        }
    }
}
