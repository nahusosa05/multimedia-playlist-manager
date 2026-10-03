package com.mediamanager.menu;

import java.util.Scanner;

public abstract class Menu {
    protected final Scanner sc;

    public Menu(Scanner sc) {
        this.sc = sc;
    }

    // Métodos abstractos a implementar en MenuCanales y MenuContenidos.
    public abstract void mostrarMenu();
    public abstract void ejecutar();

    // Métodos de lectura con validaciones.
    public int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
    }

    public int leerEnteroNoNegativo(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("Error: El número no puede ser negativo.");
        }
    }

    public String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Error: El texto no puede estar vacío.");
        }
    }

    public double leerDoubleNoNegativo(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(sc.nextLine().trim());
                if (valor >= 0) {
                    return valor;
                }
                System.out.println("Error: El valor no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número decimal válido.");
            }
        }
    }
}
