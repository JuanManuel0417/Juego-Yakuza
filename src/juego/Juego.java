package juego;

import armas.Cuchillo;
import armas.Glock;
import armas.Pistola;
import armas.Katana;

import personajes.Yakuza;
import personajes.Policia;
import personajes.Personaje;

import curas.Venda;
import curas.Agua;
import curas.Botiquin;

import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;

public class Juego {
    //Apartados para uso de random y que el usuario ingrese opcion
    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    public void iniciar(){
        System.out.println("BIENVENIDO AL JUEGO DE YAKUZA");

        //Creacion del jugador
        Yakuza jugador = new Yakuza("Akira", 100, 200);
        jugador.guardarCura(new Venda());
        jugador.guardarCura(new Agua());
        System.out.println("\nRecibiste curas iniciales");
        tienda(jugador);

        //Creacion de enemigos
        ArrayList<Personaje> enemigos = new ArrayList<>();

        Policia enemigo1 = new Policia("Tanaka", 100);
        enemigo1.guardarArma(new Pistola());
        enemigo1.guardarCura(new Botiquin());
        enemigos.add(enemigo1);

        Policia enemigo2 = new Policia("Minato", 120);
        enemigo2.guardarArma(new Pistola());
        enemigo2.guardarCura(new Botiquin());
        enemigo2.guardarCura(new Venda());
        enemigos.add(enemigo2);

        Policia enemigo3 = new Policia("Sasuke", 120);
        enemigo3.guardarArma(new Glock());
        enemigo3.guardarCura(new Botiquin());
        enemigo3.guardarCura(new Venda());
        enemigo3.guardarCura(new Agua());
        enemigos.add(enemigo3);

        int[] recompensas = {100, 250};

        for(int i = 0; i < enemigos.size(); i++){
            System.out.println("\n===== NIVEL " + (i + 1) + " =====");
            Personaje enemigo = enemigos.get(i);
            boolean victoria = combate(jugador, enemigo);

            if(!victoria){
                break;
            }

            if(i < recompensas.length){
                jugador.subirNivel();
                jugador.restaurarVida();
                jugador.reiniciarEsquives();
                jugador.ganarDinero(recompensas[i]);
                System.out.println("\nPASASTE AL NIVEL " + (i + 2));
                tienda(jugador);
            } else {
                System.out.println("\nFELICIDADES");
                System.out.println("Ahora eres el líder Yakuza");
            }
        }
    }

    public Yakuza crearJugadorAleatorio(String nombre){
        int dinero = 100 + random.nextInt(151);
        Yakuza jugador = new Yakuza(nombre, 100, dinero);
        jugador.guardarCura(new Agua());
        jugador.guardarCura(new Venda());
        if(random.nextBoolean()){
            jugador.guardarCura(new Botiquin());
        }
        jugador.guardarArma(new Cuchillo());
        return jugador;
    }

    public Policia crearEnemigoAleatorio(int nivel){
        int vida = 80 + random.nextInt(41);
        Policia enemigo = new Policia("Policia " + nivel, vida);
        int tipoArma = random.nextInt(3);
        if(tipoArma == 0){
            enemigo.guardarArma(new Pistola());
        } else if(tipoArma == 1){
            enemigo.guardarArma(new Glock());
        } else {
            enemigo.guardarArma(new Cuchillo());
        }
        enemigo.guardarCura(new Botiquin());
        if(random.nextBoolean()){
            enemigo.guardarCura(new Venda());
        }
        return enemigo;
    }

    public int calcularPuntaje(Yakuza jugador){
        return jugador.getNivel() * 100 + jugador.getDinero();
    }

    //Este es el metodo que crea la tienda como tal
    public void tienda(Yakuza jugador){
        int opcion;

        do{
            System.out.println("\n===== TIENDA =====");

            System.out.println("Dinero disponible: $" + jugador.getDinero());
            System.out.println("\n1. Katana $100");
            System.out.println("2. Pistola $150");
            System.out.println("3. Cuchillo $50");
            System.out.println("4. Glock $250");
            System.out.println("5. Venda $30");
            System.out.println("6. Agua $50");
            System.out.println("7. Botiquin $80");
            System.out.println("8. Continuar");
            System.out.print("Opcion: ");
            opcion = leerEnteroSeguro();

            switch(opcion){
                case 1:
                    if(jugador.tieneDinero(100)){
                        jugador.gastarDinero(100);
                        jugador.guardarArma(new Katana());
                        System.out.println("Compraste Katana");
                    }else{
                        System.out.println("No tienes dinero");
                    }
                    break;

                case 2:
                    if(jugador.tieneDinero(150)){
                        jugador.gastarDinero(150);
                        jugador.guardarArma(new Pistola());
                        System.out.println("Compraste Pistola");
                    }else{
                        System.out.println("No tienes dinero");
                    }
                    break;

                case 3:
                    if(jugador.tieneDinero(50)){
                        jugador.gastarDinero(50);
                        jugador.guardarArma(new Cuchillo());
                        System.out.println("Compraste Cuchillo");
                    }else{
                        System.out.println("No tienes dinero");
                    }
                    break;

                case 4:
                    if(jugador.tieneDinero(250)){
                        jugador.gastarDinero(250);
                        jugador.guardarArma(new Glock());
                        System.out.println("Compraste Glock");
                    }else{
                        System.out.println("No tienes dinero");
                    }
                    break;

                case 5:
                    if(jugador.tieneDinero(30)){
                        jugador.gastarDinero(30);
                        jugador.guardarCura(new Venda());
                        System.out.println("Compraste Venda");
                    }else{
                        System.out.println("No tienes dinero");
                    }
                    break;

                case 6:
                    if(jugador.tieneDinero(50)){
                        jugador.gastarDinero(50);
                        jugador.guardarCura(new Agua());
                        System.out.println("Compraste Agua");
                    }else{
                        System.out.println("No tienes dinero");
                    }
                    break;

                case 7:
                    if(jugador.tieneDinero(80)){
                        jugador.gastarDinero(80);
                        jugador.guardarCura(new Botiquin());
                        System.out.println("Compraste Botiquin");
                    }else{
                        System.out.println("No tienes dinero");
                    }
                    break;

                case 8:
                    System.out.println("Saliendo de tienda");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }while(opcion != 8);

        // Aca se valida el arma para no generar un error
        if(jugador.getArmaEquipada() == null){
            System.out.println("\nDebes comprar un arma");
            tienda(jugador);
        }
    }


    //Metodo para accionar el combate
    public boolean combate(Yakuza jugador, Personaje enemigo){
        while(jugador.estaVivo() && enemigo.estaVivo()){
            System.out.println("\n===== COMBATE =====");

            //Estado del Jugador
            System.out.println(jugador);
            jugador.mostrarInventarioArmas();
            jugador.mostrarInventarioCuras();

            //Estado del enemigo
            System.out.println("\n===== ENEMIGO =====");
            System.out.println(enemigo);

            //Menu de acciones del combate
            System.out.println("\n1. Atacar");
            System.out.println("2. Usar cura");
            System.out.println("3. Esquivar");
            System.out.println("4. Cambiar arma");
            System.out.print("Opcion: ");

            int opcion = leerEnteroSeguro();
            boolean esquivo = false;

            switch(opcion){
                case 1:
                    jugador.atacar(enemigo);
                    break;

                case 2:
                    jugador.mostrarInventarioCuras();
                    System.out.print("Elige cura: ");
                    int cura = leerEnteroSeguro();
                    jugador.usarCura(cura - 1);
                    break;

                case 3:
                    esquivo = jugador.esquivar();
                    break;

                case 4:
                    jugador.mostrarInventarioArmas();
                    System.out.print("Elige arma: ");
                    int arma = leerEnteroSeguro();
                    jugador.cambiarArma(arma - 1);
                    break;

                default:
                    System.out.println("Opcion invalida");
            }


            //Aca se pasa ya al turno del enemigo
            if(enemigo.estaVivo()){
                System.out.println("\n===== TURNO ENEMIGO =====");
                int accion = random.nextInt(2);


                if(accion == 0){
                    if(!esquivo){
                        enemigo.atacar(jugador);
                    }else{
                        System.out.println(enemigo.getNombre() + " fallo el ataque");
                    }
                }else{
                    // El enemigo usa cura si tiene, si no ataca
                    if(enemigo.getCantidadCuras() > 0){
                        enemigo.usarCura(0);
                    }else{
                        if(!esquivo){
                            enemigo.atacar(jugador);
                        }else{
                            System.out.println(enemigo.getNombre() + " fallo el ataque");
                        }
                    }
                }
            }
        }

        // Se muestra el resultado
        if(jugador.estaVivo()){
            System.out.println("\nGANASTE EL COMBATE");
            return true;
        }else{
            System.out.println("\nPERDISTE");
            return false;
        }
    }

    private int leerEnteroSeguro(){
        while(true){
            if(sc.hasNextInt()){
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            }
            System.out.println("Entrada invalida. Intenta de nuevo.");
            sc.nextLine();
        }
    }
}