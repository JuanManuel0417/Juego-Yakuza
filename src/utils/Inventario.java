package utils;

import java.util.ArrayList;

public class Inventario<T> {
    private ArrayList<T> items;

    public Inventario(){
        items = new ArrayList<>();
    }

    public void add(T item){ items.add(item); }
    public T get(int i){ return items.get(i); }
    public void remove(int i){ items.remove(i); }
    public int size(){ return items.size(); }
    public ArrayList<T> list(){ return items; }
}
