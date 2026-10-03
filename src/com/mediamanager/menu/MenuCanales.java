package com.mediamanager.menu;

import com.mediamanager.model.Canal;
import com.mediamanager.model.ElementoMultimedia;
import com.mediamanager.repository.Repositorio;
import com.mediamanager.utils.Secuencias;

import java.util.List;
import java.util.Scanner;

public class MenuCanales extends Menu {
    private final Repositorio<Canal> repoCanales;
    private final Repositorio<ElementoMultimedia> repoContenidos;

    public MenuCanales(Scanner sc, Repositorio<Canal> repoCanales, Repositorio<ElementoMultimedia> repoContenidos) {
        super(sc);
        this.repoCanales = repoCanales;
        this.repoContenidos = repoContenidos;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("""
                ====================================
                1 - Ingresar canal
                2 - Consultar canales (Listar todos)
                3 - Consultar un canal (Por código)
                4 - Modificar un canal
                5 - Eliminar un canal
                0 - Volver
                ====================================
                """);
    }

    // Métodos auxiliares de ejecutar()
    private void ingresarCanal() {
        int codigo = Secuencias.generarCodigoCanal();
        System.out.println("**********************************************");
        String nombre = leerTextoNoVacio("Ingrese nombre del canal: ");
        System.out.println("**********************************************");
        int suscriptores = leerEnteroNoNegativo("Ingrese la cantidad de suscriptores: ");
        System.out.println("**********************************************");

        Canal nuevoCanal = new Canal(codigo, nombre, suscriptores);

        boolean estaAgregado = repoCanales.agregar(nuevoCanal);

        if (estaAgregado) {
            System.out.println("\n====================================");
            System.out.println("Canal registrado con éxito.");
            System.out.println("====================================");
        } else {
            System.out.println("\n====================================");
            System.out.println("Error: Ya existe un canal con ese código.");
            System.out.println("====================================");
        }
    }

    private void listarCanales() {
        List<Canal> canales = repoCanales.listar();
        if (canales.isEmpty()) {
            System.out.println("\n====================================");
            System.out.println("No hay canales registrados.");
            System.out.println("====================================");
            return;
        }
        System.out.println("\n====================================");
        for (Canal c: canales) {
            System.out.println(c);
        }
        System.out.println("====================================");
    }

    private void mostrarCanalesConCodigo() {
        System.out.println("\n===========[Lista de Canales]===========");
        System.out.println("#CODIGO | NOMBRE");
        System.out.println("**********************************************");
        for (Canal c : repoCanales.listar()) {
            System.out.println("#" + c.getCodigo() + " | " + c.getNombre());
        }
        System.out.println("===============================================");
    }

    private void consultarCanal() {
        mostrarCanalesConCodigo();
        System.out.println("\n**********************************************");
        int codigo = leerEnteroNoNegativo("Ingrese el código del canal que se desea el detalle: ");
        System.out.println("**********************************************");
        Canal c = repoCanales.buscarPorCodigo(codigo);

        if (c!=null) {
            System.out.println("\n====================================");
            System.out.println(c);
            System.out.println("====================================");
        } else {
            System.out.println("\n====================================");
            System.out.println("Canal no encontrado");
            System.out.println("====================================");
        }
    }

    private void modificarCanal() {
        mostrarCanalesConCodigo();
        System.out.println("\n**********************************************");
        int codigo = leerEnteroNoNegativo("Ingrese el código del canal a modificar: ");
        System.out.println("**********************************************");

        Canal c = repoCanales.buscarPorCodigo(codigo);

        if (c != null) {
            System.out.println("**********************************************");
            String nuevoNombre = leerTextoNoVacio("Nuevo nombre: ");
            System.out.println("**********************************************");
            int nuevosSuscriptores = leerEnteroNoNegativo("Nuevos suscriptores: ");
            System.out.println("**********************************************");

            c.setNombre(nuevoNombre);
            c.setSuscriptores(nuevosSuscriptores);
            System.out.println("\n====================================");
            System.out.println("Canal modificado exitosamente.");
            System.out.println("====================================");
        } else {
            System.out.println("\n====================================");
            System.out.println("Error: El canal a modificar no existe.");
            System.out.println("====================================");
        }
    }

    private void eliminarCanal() {
        mostrarCanalesConCodigo();
        System.out.println("\n**********************************************");
        int codigo = leerEnteroNoNegativo("Ingrese el código del canal a eliminar: ");
        System.out.println("**********************************************");

        Canal canal = repoCanales.buscarPorCodigo(codigo);
        if (canal == null) {
            System.out.println("\n====================================");
            System.out.println("El canal no existe.");
            System.out.println("====================================");
            return;
        }

        boolean tieneAsociados = false;
        for (ElementoMultimedia elemento : repoContenidos.listar()) {
            if (elemento.getCanal().getCodigo() == canal.getCodigo()) {
                tieneAsociados = true;
                break;
            }
        }

        if (tieneAsociados) {
            System.out.println("\n====================================");
            System.out.println("No se puede eliminar: el canal tiene videos o canciones en la playlist.");
            System.out.println("====================================");
        } else {
            repoCanales.eliminar(canal);
            System.out.println("\n====================================");
            System.out.println("Canal eliminado correctamente.");
            System.out.println("====================================");
        }
    }


    @Override
    public void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEnteroNoNegativo("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    ingresarCanal();
                    break;
                case 2:
                    listarCanales();
                    break;
                case 3:
                    consultarCanal();
                    break;
                case 4:
                    modificarCanal();
                    break;
                case 5:
                    eliminarCanal();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
            System.out.println();
        } while (opcion != 0);
    }
}
