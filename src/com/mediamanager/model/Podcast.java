package com.mediamanager.model;

public class Podcast extends ElementoMultimedia {
    private int numeroEpisodio;

    /* Constructor */
    public Podcast(int codigo, String titulo, double duracionMinutos, Canal canal, int numeroEpisodio) {
        super(codigo, titulo, duracionMinutos, canal);
        this.numeroEpisodio = numeroEpisodio;
    }

    /* Métodos de la superclase abstracta (ElementoMultimedia) con obligación de implementar */
    @Override
    public String getTipoElemento() {
        return "Podcast";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Número de episodio: " + numeroEpisodio;
    }

    @Override
    public String reproducir() {
        return "Reproduciendo podcast: " + getTitulo() + " [Episodio #" + numeroEpisodio + "]";
    }

    /* Getters y Setters */
    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }
}
