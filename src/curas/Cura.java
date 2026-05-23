package curas;

public class Cura {
    private String nombre;
    private int curacion;
    private int precio;

    public Cura (String nombre, int curacion, int precio){
        this.nombre = nombre;
        this.curacion = curacion;
        this.precio = precio;
    }

    public String getNombre(){
        return nombre;
    }

    public int getCuracion(){
        return curacion;
    }

    public int getPrecio(){
        return precio;
    }

    @Override
    public String toString(){
        return nombre + " | Cura: " + curacion + " | Precio: $" + precio;
    }
}