package com.mediamanager.utils;

public class Secuencias {
    private static int proximoCodigoCanal = 1;
    private static int proximoCodigoElementoMultimedia = 1;

    private Secuencias() {
    }

    public static int generarCodigoCanal() {
        return proximoCodigoCanal++;
    }

    public static int generarCodigoElementoMultimedia() {
        return proximoCodigoElementoMultimedia++;
    }

    /*
        Métodos para sincronizar códigos con los ejemplos en App.java que creé, ya que si
        creo 3 articulos, se me desfasan los códigos (id) de las secuencias y estaría contando mal.
     */
    public static void sincronizarCanal(int ultimoId) {
        if (ultimoId >= proximoCodigoCanal) {
            proximoCodigoCanal = ultimoId + 1;
        }
    }

    public static void sincronizarElementoMultimedia(int ultimoId) {
        if (ultimoId >= proximoCodigoElementoMultimedia) {
            proximoCodigoElementoMultimedia = ultimoId + 1;
        }
    }
}