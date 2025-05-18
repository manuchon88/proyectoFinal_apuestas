package proyectoFinal_apuestas;

public class Administrador extends Usuario {

	protected Administrador(String nombre, String correo, String contrasenia) {
		super(nombre, correo, contrasenia);
	}

	public String crearEvento(int deporte, String datos) {
		switch (deporte) {
		case 1: {
			return EventoFutbol.leerEventFutbol(datos).toString();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + deporte);
		}
    }
	
	public String crearApuesta(int deporte, String apuestaString) {
		switch (deporte) {
		case 1: {
			return ApuestaFutbol.leerApuestaFut(apuestaString).toString();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + deporte);
		}
    }
	
	public String editarApuesta(int deporte, String CadenaApuesta, String nuevaDescripcion, double nuevaCuota) {
        switch (deporte) {
		case 1: {
			ApuestaFutbol apuesta = ApuestaFutbol.leerApuestaFut(CadenaApuesta);
			apuesta.setDescripcion(nuevaDescripcion);
			apuesta.setCuota(nuevaCuota);
			return apuesta.toString();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + deporte);
		}
    }
}
