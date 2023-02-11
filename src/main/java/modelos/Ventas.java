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
public class Ventas {
    private ArrayList<Venta> lista;
    
    //Constructores
    public Ventas(){
        lista = new ArrayList();
    }
    
    //Borrar venta de la lista
    public boolean removeVenta(Venta venta){
        return lista.remove(venta);
    }
    
    //Anadir venta a la lista
    public boolean addVenta(Venta venta){
        return lista.add(venta);
    }
    
    //Recoger el tamaño de la lista
    public int size(){
        return lista.size();
    }
    
    //Recoger una venta de la lista
    public Venta getVenta(int posicion){
        return lista.get(posicion);
    }
}
