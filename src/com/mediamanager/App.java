package com.mediamanager;

import com.mediamanager.menu.MenuCanales;
import com.mediamanager.menu.MenuContenidos;
import com.mediamanager.menu.MenuPrincipal;
import com.mediamanager.model.*;
import com.mediamanager.repository.Repositorio;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Repositorio<Canal> repoCanales = new Repositorio<>();
        Repositorio<ElementoMultimedia> repoMultimedia = new Repositorio<>();

        MenuCanales menuCanales = new MenuCanales(sc, repoCanales, repoMultimedia);
        MenuContenidos menuContenidos = new MenuContenidos(sc, repoCanales, repoMultimedia);

        /* Ejemplos añadidos a la lista de reproducción */
        Canal rhcp = new Canal(1, "Red Hot Chili Peppers", 12500000);
        Canal hi = new Canal(2, "Historias Innecesarias", 2550000);
        Canal ag = new Canal(3, "Aprender de Grandes", 340000);
        repoCanales.agregar(rhcp);
        repoCanales.agregar(hi);
        repoCanales.agregar(ag);

        repoMultimedia.agregar(new Cancion(101, "Californication", 5.21, rhcp, "Californication"));
        repoMultimedia.agregar(new Video(102, "Historias Innecesarias: Las Torres Gemelas", 24.15, hi, "1080p"));
        repoMultimedia.agregar(new Cancion(103, "Otherside", 4.18, rhcp, "Californication"));
        repoMultimedia.agregar(new Podcast(104, "¿Podés amar la Matemática sin saber hacer cuentas?", 98.06, ag, 206));
        /*  Fin de ejemplos  */

        MenuCanales mc = new MenuCanales(sc, repoCanales, repoMultimedia);
        MenuContenidos mcon = new MenuContenidos(sc, repoCanales, repoMultimedia);
        MenuPrincipal mp = new MenuPrincipal(sc, mc, mcon);
        mp.ejecutar();
    }
}
