/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author F
 */
public class Venta {
    private int id;
    private Usuario usuario;
    private LocalDateTime fecha_venta;
    private ArrayList<Producto> productos;

    public Venta() {
        this.id = -1;
        this.usuario = new Usuario();
        this.fecha_venta = null;
        this.productos = new ArrayList();
    }
    
    public Venta(int id, Usuario usuario, LocalDateTime fecha_venta, ArrayList<Producto> productos) {
        this.id = id;
        this.usuario = usuario;
        this.fecha_venta = fecha_venta;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFecha_venta() {
        return fecha_venta;
    }
    
    public void setFecha_venta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.usuario);
        hash = 17 * hash + Objects.hashCode(this.fecha_venta);
        hash = 17 * hash + Objects.hashCode(this.productos);
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
        final Venta other = (Venta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.fecha_venta, other.fecha_venta)) {
            return false;
        }
        return Objects.equals(this.productos, other.productos);
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", usuario=" + usuario + ", fecha_venta=" + fecha_venta + ", productos=" + productos + '}';
    }
}
