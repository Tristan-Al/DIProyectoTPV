/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Objects;

/**
 * Clase para ayuda en la venta asociando una cantidad a un producto
 * @author Tristan
 */
public class Pair {
    private Producto producto;
    private int cantidad;

    /**
     * Constructor vacio
     */
    public Pair() {
    }

    /**
     * Crea un nuevo Pair
     * 
     * @param producto key
     * @param cantidad value
     */
    public Pair(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    //Getters y Setters.............................
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Genera un hascode para este Pair
     * El hashcode es calculado usando los dos atributos,
     * el producto y la cantidad
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.producto);
        hash = 37 * hash + this.cantidad;
        return hash;
    }

    /**
     * Comprueba si dos objetos Pair son iguales.
     * Dos Pairs son considerados iguales cuando el producto sea el mismo
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        return Objects.equals(this.producto, other.producto);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pair{");
        sb.append("producto=").append(producto);
        sb.append(", cantidad=").append(cantidad);
        sb.append('}');
        return sb.toString();
    }
    
}
