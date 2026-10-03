package com.mediamanager.model;

public class Cancion extends ElementoMultimedia{
    private String album;

    /* Constructor */
    public Cancion (int codigo, String titulo, double duracionMinutos, Canal canal, String album) {
        super(codigo, titulo, duracionMinutos, canal);
        this.album = album;
    }

    /* Métodos de la superclase abstracta (ElementoMultimedia) con obligación de implementar */
    @Override
    public String getTipoElemento() {
        return "Canción";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Album: " + album;
    }

    @Override
    public String reproducir() {
        return "Reproduciendo [" + getTitulo() + "] de " + getCanal().getNombre() + " | " + getDetalleEspecifico();
    }

    /* Getters y Setters */
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
