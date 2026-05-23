package utils;

public class TopScore {
    private String nombre;
    private int nivel;
    private int puntaje;

    public TopScore(String nombre, int nivel, int puntaje) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPuntaje() {
        return puntaje;
    }

    @Override
    public String toString() {
        return nombre + " - Nivel " + nivel + " - Puntos " + puntaje;
    }
}
