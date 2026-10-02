package com.mediamanager.model;

public class Canal {
    private int codigo;
    private String nombre;
    private int suscriptores;

    /* Constructor */
    public Canal(int codigo, String nombre, int suscriptores) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.suscriptores = suscriptores;
    }

    /* Métodos */
    /* Getters y Setters */

    @Override
    public String toString() {
        return "Canal [Código: " + codigo + " | Nombre: " + nombre + " | Suscriptores: " + suscriptores + "]";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(int suscriptores) {
        this.suscriptores = suscriptores;
    }
}
