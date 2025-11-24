package proyectoFinal_apuestas;

import java.time.LocalDate;
import java.util.ArrayList;

public class NodoHistorialApuesta {

    LocalDate fecha;
    ArrayList<Apuesta> elementos; 
    NodoHistorialApuesta izq;
    NodoHistorialApuesta der;

    public NodoHistorialApuesta(LocalDate fecha, Apuesta elemento) {
        this.fecha = fecha;
        this.elementos = new ArrayList<Apuesta>();
        this.elementos.add(elemento);
        this.izq = null;
        this.der = null;
    }
}
