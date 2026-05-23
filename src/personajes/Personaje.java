package personajes;

import armas.Arma;
import curas.Cura;
import interfaces.Atacable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Personaje implements Atacable {
    protected String nombre;
    protected int vidaMaxima;
    protected int vida;
    protected int esquives;
    protected static int contadorPersonajes = 0;
    protected ArrayList<Arma> inventarioArmas;
    protected Arma armaEquipada;
    protected ArrayList<Cura> inventarioCuras;
    protected Random random = new Random();

    //Constructor
    public Personaje(String nombre, int vida){
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.esquives = 3;
        contadorPersonajes++;
        this.inventarioArmas = new ArrayList<>();
        this.inventarioCuras = new ArrayList<>();
    }

    //Getters
    public String getNombre(){
        return nombre;
    }

    public int getVida(){
        return vida;
    }

    public int getEsquives(){
        return esquives;
    }

    public Arma getArmaEquipada(){
        return armaEquipada;
    }


    //ARMAS
    // Guardar arma en inventario
    public void guardarArma(Arma arma){
        inventarioArmas.add(arma);
        System.out.println(nombre + " obtuvo " + arma.getNombre());

        // Si no tiene arma equipada
        // se equipa automáticamente
        if(armaEquipada == null){
            armaEquipada = arma;
            System.out.println(arma.getNombre() + " fue equipada");
        }
    }

    // Mostrar armas del inventario
    public void mostrarInventarioArmas(){
        System.out.println("\n=== ARMAS ===");
        if(inventarioArmas.size() == 0){
            System.out.println("No tienes armas");
            return;
        }

        for(int i = 0; i < inventarioArmas.size(); i++){
            System.out.println((i + 1) + ". " + inventarioArmas.get(i).getNombre());
        }
    }

    // Cambiar arma equipada
    public void cambiarArma(int opcion){
        if(opcion < 0 || opcion >= inventarioArmas.size()){
            System.out.println("Arma invalida");
            return;
        }
        armaEquipada = inventarioArmas.get(opcion);
        System.out.println(nombre + " equipó " + armaEquipada.getNombre());
    }


    //DAÑO Y VIDA
    public void recibirDanio(int danio){
        vida -= danio;
        if(vida < 0){
            vida = 0;
        }
    }

    public boolean estaVivo(){
        return vida > 0;
    }

    public void restaurarVida(){
        vida = vidaMaxima;
    }

    public static int calcularDanio(int ataque, int defensa){
        int resultado = ataque - (defensa / 2);
        return Math.max(1, resultado);
    }

    public void atacar(Personaje enemigo){
        if(armaEquipada == null){
            System.out.println(nombre + " no tiene arma equipada");
            return;
        }

        int danio = calcularDanio(armaEquipada.getDanio(), enemigo.getEsquives() * 2);
        enemigo.recibirDanio(danio);
        System.out.println(nombre + " atacó a " + enemigo.getNombre());
        System.out.println("Daño realizado: " + danio);
    }

    public int atacarSilencioso(Personaje enemigo){
        if(armaEquipada == null){
            return 0;
        }
        int base = armaEquipada.getDanio() + random.nextInt(5) - 2;
        if(base < 1){
            base = 1;
        }
        int danio = calcularDanio(base, enemigo.getEsquives() * 2);
        enemigo.recibirDanio(danio);
        return danio;
    }


    // CURAS
    public void guardarCura(Cura cura){
        inventarioCuras.add(cura);
        System.out.println(nombre + " guardó " + cura.getNombre());
    }


    public void mostrarInventarioCuras(){
        System.out.println("\n=== CURAS ===");
        if(inventarioCuras.size() == 0){
            System.out.println("No tienes curas");
            return;
        }

        for(int i = 0; i < inventarioCuras.size(); i++){
            System.out.println((i + 1) + ". " + inventarioCuras.get(i).getNombre());
        }
    }

    public void usarCura(int opcion){
        if(inventarioCuras.size() == 0){
            System.out.println("No tienes curas");
            return;
        }
        if(opcion < 0 || opcion >= inventarioCuras.size()){
            System.out.println("Cura invalida");
            return;
        }

        Cura cura = inventarioCuras.get(opcion);
        vida += cura.getCuracion();

        if(vida > vidaMaxima){
            vida = vidaMaxima;
        }

        System.out.println(nombre + " usó " + cura.getNombre());
        System.out.println("Curación: +" + cura.getCuracion());
        inventarioCuras.remove(opcion);
    }

    public int usarCuraSilencioso(int opcion){
        if(inventarioCuras.size() == 0){
            return 0;
        }
        if(opcion < 0 || opcion >= inventarioCuras.size()){
            return 0;
        }

        Cura cura = inventarioCuras.get(opcion);
        int curacion = cura.getCuracion();
        vida += curacion;

        if(vida > vidaMaxima){
            vida = vidaMaxima;
        }

        inventarioCuras.remove(opcion);
        return curacion;
    }

    public boolean esquivar(){
        if(esquives > 0){
            esquives--;
            System.out.println(nombre + " esquivó el ataque");
            return true;
        }
        System.out.println(nombre + " no tiene esquives");
        return false;
    }

    public boolean esquivarSilencioso(){
        if(esquives > 0){
            esquives--;
            return random.nextInt(100) < 75;
        }
        return false;
    }

    public void reiniciarEsquives(){
        esquives = 3;
    }

    @Override
    public String toString(){

        String nombreArma = "Sin arma";

        if(armaEquipada != null){
            nombreArma = armaEquipada.getNombre();
        }
        return "\nNombre: " + nombre + "\nVida: " + vida + "/" + vidaMaxima +
                "\nArma equipada: " + nombreArma + "\nEsquives: " + esquives;
    }

    // Metodos abstractos que deben implementar las subclases
    public abstract void habilidadEspecial(Personaje objetivo);
    public abstract String getClase();

    // Overloading de atacar: versiones simples
    public void atacar(){
        System.out.println(nombre + " intenta atacar, pero no hay objetivo");
    }

    public void atacar(Personaje objetivo, int multiplicador){
        if(armaEquipada == null){
            System.out.println(nombre + " no tiene arma equipada");
            return;
        }
        objetivo.recibirDanio(armaEquipada.getDanio() * multiplicador);
        System.out.println(nombre + " atacó a " + objetivo.getNombre() + " x" + multiplicador);
        System.out.println("Daño realizado: " + (armaEquipada.getDanio() * multiplicador));
    }

    public int getCantidadCuras(){
        return inventarioCuras.size();
    }

    public int getCantidadArmas(){
        return inventarioArmas.size();
    }

    public int getVidaMaxima(){
        return vidaMaxima;
    }

    public ArrayList<Cura> getInventarioCuras(){
        return inventarioCuras;
    }

    public ArrayList<Arma> getInventarioArmas(){
        return inventarioArmas;
    }
}