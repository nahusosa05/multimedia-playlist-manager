package com.mediamanager.menu;

import java.util.Scanner;

public class MenuPrincipal extends Menu {
    private final MenuCanales menuCanales;
    private final MenuContenidos menuContenidos;

    public MenuPrincipal(Scanner sc, MenuCanales menuCanales, MenuContenidos menuContenidos) {
        super(sc);
        this.menuCanales = menuCanales;
        this.menuContenidos = menuContenidos;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("""
                ====================================
                Playlist de elementos multimedia
                ====================================
                1 - Menú de canales.
                2 - Menú de elementos en playlist.
                0 - Cerrar
                ====================================
                Nota: Debe crear un canal antes de
                crear un elemento multimedia.
                ====================================
                """);
    }

    @Override
    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEnteroNoNegativo("Ingrese su opción");
            switch (opcion) {
                case 1:
                    menuCanales.ejecutar();
                    break;
                case 2:
                    menuContenidos.ejecutar();
                    break;
                case 0:
                    System.out.println("Cerrando reproductor...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
            System.out.println();
        } while (opcion != 0);
    }
}
