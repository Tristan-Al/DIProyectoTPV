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
    private String nickname_usuario;
    private LocalDateTime fecha_venta;
    //private ArrayList<Producto> productos; Productos habiamos dicho que no se metia en ventas 

    public Venta() {
        this.id = -1;
        this.nickname_usuario = "";
        this.fecha_venta = null;
    }
    
    public Venta(int id, String nickname_usuario, LocalDateTime fecha_venta) {
        this.id = id;
        this.nickname_usuario = nickname_usuario;
        this.fecha_venta = fecha_venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname_usuario() {
        return nickname_usuario;
    }

    public void setNickname_usuario(String nickname_usuario) {
        this.nickname_usuario = nickname_usuario;
    }

    public LocalDateTime getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.nickname_usuario);
        hash = 37 * hash + Objects.hashCode(this.fecha_venta);
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
        if (!Objects.equals(this.nickname_usuario, other.nickname_usuario)) {
            return false;
        }
        return Objects.equals(this.fecha_venta, other.fecha_venta);
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", nickname_usuario=" + nickname_usuario + ", fecha_venta=" + fecha_venta + '}';
    }
}
