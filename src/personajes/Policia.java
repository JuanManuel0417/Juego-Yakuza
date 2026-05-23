package personajes;

public class Policia extends Personaje{

    public Policia(String nombre, int vida){
        super(nombre, vida);
    }

    @Override
    public void habilidadEspecial(Personaje objetivo){
        if(armaEquipada == null){
            System.out.println(nombre + " intenta habilidad especial pero no tiene arma");
            return;
        }
        int danio = armaEquipada.getDanio() + 5;
        objetivo.recibirDanio(danio);
        System.out.println(nombre + " usa Disparo Preciso en " + objetivo.getNombre());
    }

    @Override
    public String getClase(){
        return "Policia";
    }
}
