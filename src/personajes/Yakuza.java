package personajes;

//Se esta heredando de la clase personaje
public class Yakuza extends Personaje{
    private int dinero;
    private int nivel;

    //Creo el contructor llamado desde personaje pero se le agrega el atributo de dinero
    public Yakuza(String nombre, int vida, int dinero){
        super(nombre, vida);
        this.dinero = dinero;
        this.nivel = 1;
    }

    public int getDinero(){
        return dinero;
    }

    public int getNivel(){
        return nivel;
    }

    public void ganarDinero(int cantidad){
        dinero += cantidad;
    }

    public void gastarDinero(int cantidad){
        dinero -= cantidad;
    }

    public boolean tieneDinero(int precio){
        return dinero >= precio;
    }

    public void subirNivel(){
        nivel++;
        System.out.println(nombre + " subio al nivel " + nivel);
    }

    @Override
    public String toString(){
        return super.toString() + "\nDinero: $" + dinero + "\nNivel: " + nivel;
    }

    @Override
    public void habilidadEspecial(Personaje objetivo){
        if(armaEquipada == null){
            System.out.println(nombre + " intenta habilidad especial pero no tiene arma");
            return;
        }
        int danio = armaEquipada.getDanio() + 10;
        objetivo.recibirDanio(danio);
        System.out.println(nombre + " usa Golpe Fuerte en " + objetivo.getNombre());
        System.out.println("Daño realizado: " + danio);
    }

    @Override
    public String getClase(){
        return "Yakuza";
    }
}