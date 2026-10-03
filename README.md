# Gestor de Lista de Reproducción
Pre-entrega del proyecto final del curso de Java Backend - Talento Tech. Proyecto realizado con el <u>`Manual Java Poo CRUDS EXPLICADOS EN DETALLE`</u> dispuesto por la profesora Gisele González durante la cursada.

*Objetivo próximo: Incorporar SpringBoot al proyecto.*

* **Alumno:** Nahuel Sosa
* **Comisión:** 26223

---

## Descripción del Proyecto

Aplicación interactiva por consola desarrollada en Java que implementa un sistema CRUD para gestionar un reproductor de contenidos multimedia (*Canciones, Videos y Podcasts*).
Cada elemento multimedia se encuentra vinculado a un **Canal creador**, modelando relaciones entre objetos.

---

## Tecnologías y Conceptos Aplicados

* **Lenguaje:** Java SE (JDK 21)
* **Sistema operativo:** Kubuntu 26.04.1 LTS
* **Programación Orientada a Objetos (POO):**
    * *Herencia y Clases Abstractas:* `ElementoMultimedia` como base de especialización y `Menu` como plantilla para las vistas de consola.
    * *Polimorfismo y Sobreescritura:* Implementación de `@Override` en visualización y reproducción.
    * *Interfaces:* `Identificable` (garantía de clave primaria numérica y autoincremental) y `Reproducible` (comportamiento de reproducción).
    * *Generics (Tipos Genéricos):* Clase `Repositorio<T extends Identificable>` para unificar el almacenamiento y las operaciones de búsqueda/persistencia en memoria.

---

## Estructura del proyecto

El proyecto sigue una separación clara de responsabilidades distribuida en los siguientes paquetes:

```text
src/
└── com/
    └── mediamanager/
        ├── interfaces/
        │   ├── Identificable.java        # Contrato para entidades con código único
        │   └── Reproducible.java         # Contrato para simulación de reproducción
        ├── model/
        │   ├── Canal.java                # Entidad del Canal creador de un elemento multimedia
        │   ├── ElementoMultimedia.java   # Clase abstracta base para la playlist
        │   ├── Cancion.java              # Subtipo concreto (canción)
        │   ├── Video.java                # Subtipo concreto (video)
        │   └── Podcast.java              # Subtipo concreto (podcast)
        ├── repository/
        │   └── Repositorio.java          # Estructura genérica de datos en memoria
        ├── menu/
        │   ├── Menu.java                 # Clase base con validación de entradas de consola
        │   ├── MenuCanales.java          # Menú y operaciones CRUD de Canales
        │   └── MenuContenidos.java       # Menú y operaciones CRUD de la Playlist
        │   └── MenuPrincipal.java        # Menú principal
        ├── utils/
        │   ├── Validador.java            # Métodos estáticos puros para validaciones lógicas
        │   └── Secuencias.java           # Generador secuencial de códigos autoincrementales
        └── App.java                      # Clase ejecutable con ejemplos y menú principal cargados.
```