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

    public void llenarListaOrdenadaDesc(ArrayList<Apuesta> destino) {
        recorrerDesc(raiz, destino);
    }

    private void recorrerDesc(NodoHistorialApuesta nodo, ArrayList<Apuesta> destino) {
        if (nodo == null) {
            return;
        }

        recorrerDesc(nodo.der, destino);
        for (int i = 0; i < nodo.elementos.size(); i++) {
            Apuesta ap = nodo.elementos.get(i);
            destino.add(ap);
        }

        recorrerDesc(nodo.izq, destino);
    }
}
