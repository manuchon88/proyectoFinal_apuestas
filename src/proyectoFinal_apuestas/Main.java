package proyectoFinal_apuestas;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<ApuestaFutbol> apues = ApuestaFutbol.leerApuestaFutbolTxt(Archivos.archivosApuestasFutbol);
		//System.out.println(VentanaApuestasConCompetencia.apuestasFut(apues.get(0).getEvent()));
	}

}
