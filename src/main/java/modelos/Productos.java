/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;

/**
 *
 * @author rbare
 */
public class Productos {
    private ArrayList<Producto> lista;
    
    //Constructores
    public Productos(){
        lista = new ArrayList();
    }
    
    //Borrar un producto de la lista
    public boolean removeProducto(Producto producto){
        return lista.remove(producto);
    }
    
    //Anadir un producto a la lista
    public boolean addProducto(Producto producto){
        return lista.add(producto);
    }
    
    //Recoger el tamaño de la lista
    public int size(){
        return lista.size();
    }
    
    //Recoger un producto de la lista
    public Producto getProducto(int posicion){
        System.out.println("Posicion que se la pasa el getProducto" + posicion);
        return lista.get(posicion);
    }
}
