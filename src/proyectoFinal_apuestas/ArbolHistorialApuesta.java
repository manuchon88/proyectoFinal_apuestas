package proyectoFinal_apuestas;

import java.time.LocalDate;
import java.util.ArrayList;

public class ArbolHistorialApuesta {

    private NodoHistorialApuesta raiz;

    public ArbolHistorialApuesta() {
        this.raiz = null;
    }

    public void insertar(LocalDate fecha, Apuesta elemento) {
        if (raiz == null) {
            raiz = new NodoHistorialApuesta(fecha, elemento);
        } else {
            insertarRec(fecha, elemento, raiz);
        }
    }

    private void insertarRec(LocalDate fecha, Apuesta elemento, NodoHistorialApuesta nodo) {
        if (fecha.isBefore(nodo.fecha)) { 
            if (nodo.izq == null) {
                nodo.izq = new NodoHistorialApuesta(fecha, elemento);
            } else {
                insertarRec(fecha, elemento, nodo.izq);
            }

        } else if (fecha.isAfter(nodo.fecha)) {
            if (nodo.der == null) {
                nodo.der = new NodoHistorialApuesta(fecha, elemento);
            } else {
                insertarRec(fecha, elemento, nodo.der);
            }

        } else {
            nodo.elementos.add(elemento);
        }
    }

    public void llenarListaOrdenadaDesc(ArrayList<Apuesta> lista_ordenadas) {
        recorrerDesc(raiz, lista_ordenadas);
    }

    private void recorrerDesc(NodoHistorialApuesta nodo, ArrayList<Apuesta> lista_ordenadas) {
        if (nodo == null) {
            return;
        }

        recorrerDesc(nodo.der, lista_ordenadas);
        for (int i = 0; i < nodo.elementos.size(); i++) {
            Apuesta ap = nodo.elementos.get(i);
            lista_ordenadas.add(ap);
        }

        recorrerDesc(nodo.izq, lista_ordenadas);
    }
    
    
    // ==== NUEVO: recorrido descendente SOLO en el rango [desde, hasta] ====
    public void llenarListaOrdenadaDescRango(LocalDate desde, LocalDate hasta, ArrayList<Apuesta> lista_ordenadas) {
        recorrerDescRango(raiz, desde, hasta, lista_ordenadas);
    }

    private void recorrerDescRango(NodoHistorialApuesta nodo, LocalDate desde, LocalDate hasta,
                                   ArrayList<Apuesta> lista_ordenadas) {
        if (nodo == null) {
            return;
        }

        // Si la fecha del nodo es mayor que "hasta", solo tiene sentido ir a la izquierda
        if (nodo.fecha.isAfter(hasta)) {
            recorrerDescRango(nodo.izq, desde, hasta, lista_ordenadas);
            return;
        }

        // Si la fecha del nodo es menor que "desde", solo tiene sentido ir a la derecha
        if (nodo.fecha.isBefore(desde)) {
            recorrerDescRango(nodo.der, desde, hasta, lista_ordenadas);
            return;
        }

        // Si está dentro del rango, hacemos el in-order descendente normal
        recorrerDescRango(nodo.der, desde, hasta, lista_ordenadas);

        for (int i = 0; i < nodo.elementos.size(); i++) {
            Apuesta ap = nodo.elementos.get(i);
            lista_ordenadas.add(ap);
        }

        recorrerDescRango(nodo.izq, desde, hasta, lista_ordenadas);
    }
}
