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
public class Usuario {
    
    private String nickname;
    private String nombre;
    private String apellidos;
    private int password;
    private int rol;

    /**
     * Como predeterminado los unicos que pueden ser nulos es el nombre y el
     * apellido los demas son una cadena vacia
     */
    
    public Usuario() {
        this.nickname = "";
        this.nombre = null;
        this.apellidos = null;
        this.password = -1;
        this.rol = 1;
    }
    
    public Usuario(String nickname, String nombre, String apellidos, int password, int rol) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.rol = rol;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" 
                + "\n \t nickname=" + nickname 
                + "\n \t nombre=" + nombre 
                + "\n \t apellidos=" + apellidos 
                + "\n \t password=" + password 
                + "\n \t rol=" + rol 
                + "}\n";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.nickname);
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.apellidos);
        hash = 53 * hash + Objects.hashCode(this.password);
        hash = 53 * hash + Objects.hashCode(this.rol);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return Objects.equals(this.rol, other.rol);
    }    
}
