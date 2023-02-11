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
public class Usuarios {
    private ArrayList<Usuario> lista;
    
    //Constructores
    public Usuarios(){
        lista = new ArrayList();
    }
    
    //Borrar usuario de la lista
    public boolean removeUsuario(Usuario usuario){
        return lista.remove(usuario);
    }
    
    //Anadir usuario a la lista
    public boolean addUsuario(Usuario usuario){
        return lista.add(usuario);
    }
    
    //Recoger el tamaño de la lista
    public int size(){
        return lista.size();
    }
    
    //Recoger un usuario de la lista
    public Usuario getUsuario(int posicion){
        return lista.get(posicion);
    }
}
