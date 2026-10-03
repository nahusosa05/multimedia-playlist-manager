package com.mediamanager.menu;

import com.mediamanager.model.*;
import com.mediamanager.repository.Repositorio;
import com.mediamanager.utils.Secuencias;

import java.util.List;
import java.util.Scanner;

public class MenuContenidos extends Menu {
    private final Repositorio<Canal> repoCanales;
    private final Repositorio<ElementoMultimedia> repoContenidos;

    public MenuContenidos(Scanner sc, Repositorio<Canal> repoCanales, Repositorio<ElementoMultimedia> repoContenidos) {
        super(sc);
        this.repoCanales = repoCanales;
        this.repoContenidos = repoContenidos;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("""
                ====================================
                1 - Ingresar elemento a la playlist (Canción, Video o Podcast).
                2 - Listar playlist completa.
                3 - Consultar detalle de un elemento de la playlist.
                4 - Modificar elemento de la playlist.
                5 - Eliminar elemento de la playlist.
                6 - Reproducir elemento de la playlist.
                0 - Volver
                ====================================
                """);
    }

    // Métodos auxiliares de ejecutar()
    private void mostrarCanalesConCodigo() {
        System.out.println("\n===========[Lista de Canales]===========");
        System.out.println("#CODIGO | NOMBRE");
        System.out.println("**********************************************");
        for (Canal c : repoCanales.listar()) {
            System.out.println("#" + c.getCodigo() + " | " + c.getNombre());
        }
        System.out.println("===============================================");
    }

    private Canal seleccionarCanal() {
        mostrarCanalesConCodigo();
        System.out.println("\n**********************************************");
        int codigoCanal = leerEnteroNoNegativo("Ingrese el código del canal creador: ");
        System.out.println("**********************************************");
        Canal canal = repoCanales.buscarPorCodigo(codigoCanal);

        if (canal == null) {
            System.out.println("\n====================================");
            System.out.println("Error: No existe un canal con el código " + codigoCanal + ".");
            System.out.println("Debe crearlo previamente desde el menú de Canales.");
            System.out.println("====================================");
            return null;
        }
        return canal;
    }

    private void ingresarCancion() {
        int codigo = Secuencias.generarCodigoElementoMultimedia();

        System.out.println("**********************************************");
        String titulo = leerTextoNoVacio("Ingrese título de la canción: ");
        System.out.println("**********************************************");
        double duracion = leerDoubleNoNegativo("Ingrese la duración de la canción (Minutos.Segundos): ");
        System.out.println("**********************************************");
        Canal canal = seleccionarCanal();

        if (canal == null) {
            return;
        }
        System.out.println("**********************************************");
        String album = leerTextoNoVacio("Ingrese el álbum de la canción: ");
        System.out.println("**********************************************");

        Cancion nuevaCancion = new Cancion(codigo, titulo, duracion, canal, album);

        if (repoContenidos.agregar(nuevaCancion)) {
            System.out.println("\n====================================");
            System.out.println("Canción agregada exitosamente a la playlist.");
            System.out.println("====================================");
        }
    }
    private void ingresarVideo() {
        int codigo = Secuencias.generarCodigoElementoMultimedia();

        System.out.println("**********************************************");
        String titulo = leerTextoNoVacio("Ingrese título del video: ");
        System.out.println("**********************************************");
        double duracion = leerDoubleNoNegativo("Ingrese la duración del video (Minutos.Segundos): ");
        System.out.println("**********************************************");
        Canal canal = seleccionarCanal();

        if (canal == null) {
            return;
        }
        System.out.println("**********************************************");
        String calidad = leerTextoNoVacio("Ingrese la calidad del video: ");
        System.out.println("**********************************************");

        Video nuevoVideo = new Video(codigo, titulo, duracion, canal, calidad);

        if (repoContenidos.agregar(nuevoVideo)) {
            System.out.println("\n====================================");
            System.out.println("Video agregado exitosamente a la playlist.");
            System.out.println("====================================");
        }
    }
    private void ingresarPodcast() {
        int codigo = Secuencias.generarCodigoElementoMultimedia();

        System.out.println("**********************************************");
        String titulo = leerTextoNoVacio("Ingrese título del podcast: ");
        System.out.println("**********************************************");
        double duracion = leerDoubleNoNegativo("Ingrese la duración del podcast (Minutos.Segundos): ");
        System.out.println("**********************************************");
        Canal canal = seleccionarCanal();

        if (canal == null) {
            return;
        }
        System.out.println("**********************************************");
        int nroEpisodio = leerEnteroNoNegativo("Ingrese el número de episodio: ");
        System.out.println("**********************************************");

        Podcast nuevoPodcast = new Podcast(codigo, titulo, duracion, canal, nroEpisodio);

        if (repoContenidos.agregar(nuevoPodcast)) {
            System.out.println("\n====================================");
            System.out.println("Podcast agregado exitosamente a la playlist.");
            System.out.println("====================================");
        }
    }
    private void ingresarContenido() {
        int opcion;
        do {
            System.out.println("""
                ====================================
                ¿Qué desea agregar?
                ====================================
                1 - Canción
                2 - Video
                3 - Podcast
                0 - Volver
                ====================================
                """);
            opcion = leerEnteroNoNegativo("Seleccione la opción: ");

            switch (opcion) {
                case 1:
                    ingresarCancion();
                    break;
                case 2:
                    ingresarVideo();
                    break;
                case 3:
                    ingresarPodcast();
                    break;
                case 0:
                    System.out.println("Volviendo a menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private String formatearDuracion(double duracion) {
        int minutos = (int) duracion;
        int segundos = (int) Math.round((duracion - minutos) * 100);

        if (segundos >= 60) {
            minutos += segundos / 60;
            segundos = segundos % 60;
        }

        return minutos + ":" + segundos;
    }

    private void listarElementos() {
        List<ElementoMultimedia> contenidos = repoContenidos.listar();
        if (contenidos.isEmpty()) {
            System.out.println("\n====================================");
            System.out.println("No hay contenidos registrados.");
            System.out.println("====================================");
            return;

        }

        System.out.println("\n===========[LISTA DE REPRODUCCIÓN]===========");
        for (ElementoMultimedia em : contenidos) {
            String nombreCanal = (em.getCanal() != null) ? em.getCanal().getNombre() : "Sin canal";
            String tiempoFormateado = formatearDuracion(em.getDuracionMinutos());
            System.out.println("#" + em.getCodigo() + " - " + em.getTitulo() + " | " + nombreCanal + " | " + tiempoFormateado);
        }
        System.out.println("===============================================");

    }

    private void consultarElemento() {
        mostrarElementosConCodigo();
        System.out.println("\n**********************************************");
        int codigo = leerEnteroNoNegativo("Ingrese el código del elemento que desea el detalle: ");
        System.out.println("**********************************************");
        ElementoMultimedia em = repoContenidos.buscarPorCodigo(codigo);

        if (em != null) {
            System.out.println("\n====================================");
            System.out.println(em);
            System.out.println("====================================");
        } else {
            System.out.println("\n====================================");
            System.out.println("Elemento multimedia no encontrado");
            System.out.println("====================================");
        }
    }

    private void mostrarElementosConCodigo() {
        System.out.println("\n===========[Lista de Elementos]===========");
        System.out.println("#CODIGO | TÍTULO");
        System.out.println("**********************************************");
        for (ElementoMultimedia em : repoContenidos.listar()) {
            System.out.println("#" + em.getCodigo() + " | " + em.getTitulo());
        }
        System.out.println("===============================================");
    }

    private void modificarElemento() {
        mostrarElementosConCodigo();
        System.out.println("\n**********************************************");
        int codigo = leerEnteroNoNegativo("Ingrese el código del elemento a modificar: ");
        ElementoMultimedia em = repoContenidos.buscarPorCodigo(codigo);

        if (em == null) {
            System.out.println("\n====================================");
            System.out.println("Error: El elemento a modificar no existe.");
            System.out.println("====================================");
            return;
        }

        System.out.println("\nModificando: " + em.getTitulo() + " [" + em.getTipoElemento() + "]");
        em.setTitulo(leerTextoNoVacio("Nuevo título: "));
        em.setDuracionMinutos(leerDoubleNoNegativo("Nueva duración: "));

        if (em instanceof Cancion cancion) {
            cancion.setAlbum(leerTextoNoVacio("Nuevo álbum: "));
        } else if (em instanceof Video video) {
            video.setCalidad(leerTextoNoVacio("Nueva calidad/resolución: "));
        } else if (em instanceof Podcast podcast) {
            podcast.setNumeroEpisodio(leerEnteroNoNegativo("Nuevo número de episodio: "));
        }
        System.out.println("\n====================================");
        System.out.println("Elemento modificado exitosamente.");
        System.out.println("====================================");
    }

    private void eliminarElemento() {
        mostrarElementosConCodigo();
        System.out.println("\n**********************************************");
        int codigo = leerEnteroNoNegativo("Ingrese el código del elemento a eliminar: ");
        System.out.println("**********************************************");

        ElementoMultimedia em = repoContenidos.buscarPorCodigo(codigo);
        if (em == null) {
            System.out.println("\n====================================");
            System.out.println("Error: Elemento no encontrado.");
            System.out.println("====================================");
            return;
        }

        repoContenidos.eliminar(em);
        System.out.println("\n====================================");
        System.out.println("Elemento eliminado correctamente.");
        System.out.println("====================================");

    }

    private void reproducirElemento() {
        mostrarElementosConCodigo();
        System.out.println("\n**********************************************");
        int codigo = leerEnteroNoNegativo("Ingrese el código del elemento a reproducir: ");
        System.out.println("**********************************************");
        ElementoMultimedia em = repoContenidos.buscarPorCodigo(codigo);

        if (em != null) {
            System.out.println("\n====================================");
            System.out.println(em.reproducir());
            System.out.println("====================================");
        } else {
            System.out.println("\n====================================");
            System.out.println("Error: Elemento no encontrado.");
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
                    ingresarContenido();
                    break;
                case 2:
                    listarElementos();
                    break;
                case 3:
                    consultarElemento();
                    break;
                case 4:
                    modificarElemento();
                    break;
                case 5:
                    eliminarElemento();
                    break;
                case 6:
                    reproducirElemento();
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
