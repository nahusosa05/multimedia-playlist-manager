package com.mediamanager.model;

import com.mediamanager.interfaces.Reproducible;

public abstract class ElementoMultimedia implements Reproducible {
    private int codigo;
    private String titulo;
    private double duracionMinutos;
    private Canal canal; // Un ElementoMultimedia tiene un Canal.

    /* Constructor */
    public ElementoMultimedia(int codigo, String titulo, double duracionMinutos, Canal canal) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.duracionMinutos = duracionMinutos;
        this.canal = canal;
    }

    /* Métodos abstractos */

    // Retorna el tipo de ElementoMultimedia: Canción, Video, Podcast.
    public abstract String getTipoElemento();

    // Retorna la información detallada de las subclases que hereden ElementoMultimedia.
    public abstract String getDetalleEspecifico();

    @Override
    public String toString() {
        return  "ID: " + codigo + "\n" +
                "Titulo: " + titulo + "\n" +
                "Duración: " + duracionMinutos + "\n" +
                "Canal: " + (canal != null ? canal.getNombre() : "Sin canal") + "\n" +
                "Elemento multimedia: " + this.getTipoElemento() + "\n" +
                "Detalle del elemento: " + this.getDetalleEspecifico();
    }

    /* Getters y Setters */
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(double duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }
}
