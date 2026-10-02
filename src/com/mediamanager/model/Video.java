package com.mediamanager.model;

public class Video extends ElementoMultimedia{
    private String calidad;

    /* Constructor */
    public Video(int codigo, String titulo, double duracionMinutos, Canal canal, String calidad) {
        super(codigo, titulo, duracionMinutos, canal);
        this.calidad = calidad;
    }

    /* Métodos de la superclase abstracta (ElementoMultimedia) con obligación de implementar */
    @Override
    public String getTipoElemento() {
        return "Video";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Calidad: " + calidad;
    }

    @Override
    public String reproducir() {
        return "Reproduciendo video [" + getTitulo() + "] a resolución [" + calidad + "]";
    }

    /* Getters y Setters */
    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }
}
