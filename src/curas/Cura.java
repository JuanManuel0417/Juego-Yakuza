package curas;

import java.util.Random;

public class Cura {
    private String nombre;
    private int curacionMin;
    private int curacionMax;
    private int precioMin;
    private int precioMax;
    private Random random = new Random();

    public Cura(String nombre, int curacionMin, int curacionMax, int precioMin, int precioMax){
        this.nombre = nombre;
        this.curacionMin = curacionMin;
        this.curacionMax = curacionMax;
        this.precioMin = precioMin;
        this.precioMax = precioMax;
    }

    public String getNombre(){
        return nombre;
    }

    public int getCuracion(){
        return curacionMin + random.nextInt(curacionMax - curacionMin + 1);
    }

    public int getPrecio(){
        return precioMin + random.nextInt(precioMax - precioMin + 1);
    }

    public int getCuracionPromedia(){
        return (curacionMin + curacionMax) / 2;
    }

    @Override
    public String toString(){
        return nombre + " | Cura: " + curacionMin + "-" + curacionMax + " | Precio: $" + precioMin + "-" + precioMax;
    }
}
