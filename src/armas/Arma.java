package armas;

import java.util.Random;

//Esta clase es el molde padre de todas las armas
public class Arma {
    //Asignacion de atributos, privados para que no se modifiquen fuera de la clase
    private String nombre;
    private int danioMin;
    private int danioMax;
    private int precioMin;
    private int precioMax;
    private Random random = new Random();

    //Constructor con rangos de daño y precio
    public Arma(String nombre, int danioMin, int danioMax, int precioMin, int precioMax){
        this.nombre = nombre;
        this.danioMin = danioMin;
        this.danioMax = danioMax;
        this.precioMin = precioMin;
        this.precioMax = precioMax;
    }

    //Se les da acceso con get para leer los valores en otras partes del programa
    public String getNombre(){
        return nombre;
    }

    public int getDanio(){
        return danioMin + random.nextInt(danioMax - danioMin + 1);
    }

    public int getPrecio(){
        return precioMin + random.nextInt(precioMax - precioMin + 1);
    }

    public int getDanioPromedio(){
        return (danioMin + danioMax) / 2;
    }

    //Sobrescritura con el texto asigando
    @Override
    public String toString(){
        return nombre + " | Daño: " + danioMin + "-" + danioMax + " | Precio: $" + precioMin + "-" + precioMax;
    }
}
