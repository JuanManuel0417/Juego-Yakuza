package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Top5Manager {
    private String archivo = "top5.txt";

    public ArrayList<TopScore> cargarTop5() {
        ArrayList<TopScore> lista = new ArrayList<TopScore>();
        try {
            File file = new File(archivo);
            if (!file.exists()) {
                return lista;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    String nombre = partes[0];
                    int nivel = Integer.parseInt(partes[1]);
                    int puntaje = Integer.parseInt(partes[2]);
                    lista.add(new TopScore(nombre, nivel, puntaje));
                }
            }
            reader.close();
        } catch (Exception e) {
            // Si hay error, no hacemos nada para no interrumpir el juego.
        }
        return lista;
    }

    public void guardarPuntaje(String nombre, int nivel, int puntaje) {
        ArrayList<TopScore> lista = cargarTop5();
        lista.add(new TopScore(nombre, nivel, puntaje));
        Collections.sort(lista, new Comparator<TopScore>() {
            public int compare(TopScore a, TopScore b) {
                return b.getPuntaje() - a.getPuntaje();
            }
        });
        while (lista.size() > 5) {
            lista.remove(lista.size() - 1);
        }
        guardarArchivo(lista);
    }

    private void guardarArchivo(ArrayList<TopScore> lista) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
            for (TopScore item : lista) {
                writer.write(item.getNombre() + ";" + item.getNivel() + ";" + item.getPuntaje());
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            // Error de escritura, lo ignoramos en esta versión.
        }
    }
}
