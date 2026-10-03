package com.mediamanager.utils;

// Se usa <final> para indicar que no debería heredarse.
public final class Validaciones {
    // Esto impide instanciar el objeto.
    private Validaciones() {}

    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean validarNoNegativo(int numero) {
        return numero >= 0;
    }

    public static boolean validarNoNegativo(double numero) {
        return numero >= 0.0;
    }

    public static boolean esDuracionValida(double duracion) {
        if (duracion < 0.0) return false;

        int minutos = (int) duracion;
        int segundos = (int) Math.round((duracion - minutos) * 100);
        return segundos >= 0 && segundos < 60;
    }
}