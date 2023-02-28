/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Objects;

/**
 *
 * @author F
 */
public class Producto {
    private int id_producto;
    private String nombre;
    private double precio;
    private int stock;

    public Producto() {
        this.id_producto = -1;
        this.nombre = "";
        this.precio = 0.0;
        this.stock = 0;
    }
    
    public Producto(int id_producto, String nombre, double precio, int stock) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    
    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id_producto;
        hash = 71 * hash + Objects.hashCode(this.nombre);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        hash = 71 * hash + this.stock;
        return hash;
    }

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
        final Producto other = (Producto) obj;
        if (this.id_producto != other.id_producto) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (this.stock != other.stock) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public String toString() {
        return "Producto{" 
                + "\n \t id_producto=" + id_producto 
                + "\n \t nombre=" + nombre 
                + "\n \t precio=" + precio 
                + "\n \t stock=" + stock 
                + "} \n";
    }
}
