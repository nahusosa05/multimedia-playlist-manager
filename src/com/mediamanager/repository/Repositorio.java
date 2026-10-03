package com.mediamanager.repository;

import com.mediamanager.interfaces.Identificable;

import java.util.ArrayList;
import java.util.List;

/*
 * Es necesario esta clase Repositorio, ya que:
 *  En App.java no se tratan por separado los 2 CRUD's distintos:
 *  - ArrayList<ElementoMultimedia> elementosMultimedias
 *  - ArrayList<Canal> canales
 *
 * Ya que tanto Canal como ElementoMultimedia comparten los mismos métodos:
 *  - agregar()
 *  - listar()
 *  - buscarPorCodigo()
 *  - eliminar()
 *
 * Nota: '<T extends Identificable>' Esto significa que T puede ser cualquier tipo, siempre
 *       que implemente Identificable ( getCodigo() ).
 * */

public class Repositorio<T extends Identificable> {
    private final ArrayList<T> lista;

    /* Constructor */
    public Repositorio() {
        this.lista = new ArrayList<>();
    }

    public boolean agregar(T objeto) {
        if(objeto == null) {
            return false;
        }
        if (buscarPorCodigo(objeto.getCodigo()) != null) {
            return false;
        }

        return lista.add(objeto);
    }
    
    public List<T> listar() {
        return new ArrayList<>(lista);
    }

    public T buscarPorCodigo(int codigo) {
        for(T objeto : lista) {
            if(objeto.getCodigo() == codigo) {
                return objeto;
            }
        }

        return null;
    }

    public boolean eliminar(T objeto) {
        if (objeto == null ) {
            return false;
        }

        return lista.remove(objeto);
    }

    public int cantidad() {
        return lista.size();
    }
}
