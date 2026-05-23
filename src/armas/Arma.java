package armas;

//Esta clase es el molde padre de todas las armas
public class Arma {
    //Asignacion de atributos, privados para que no se modifiquen fuera de la clase
    private String nombre;
    private int danio;
    private int precio;

    //Constructor
    public Arma(String nombre, int danio, int precio){
        this.nombre = nombre;
        this.danio = danio;
        this.precio = precio;
    }

    //Se les da acceso con get para leer los valores en otras partes del programa
    public String getNombre(){
        return nombre;
    }

    public int getDanio(){
        return  danio;
    }

    public int getPrecio(){
        return precio;
    }

    //Sobrescritura con el texto asigando
    @Override
    public String toString(){
        return nombre + " | Daño: " + danio + " | Precio: $" + precio;
    }
}