package proyectoFinal_apuestas;

public class Administrador extends Usuario {

	protected Administrador(String nombre, String correo, String contrasenia) {
		super(nombre, correo, contrasenia);
	}

	public Evento crearEvento(String tipo, int anio, int mes, int dia, String equipo1, String equipo2) {
		return new EventoFutbol(anio, mes, dia, equipo1, equipo2);
    }
	
	public Apuesta crearApuesta(String descripcion, double cuota, Evento evento) {
		return new ApuestaFutbol(descripcion, cuota, (EventoFutbol) evento);
    }
	
	public void editarApuesta(Apuesta apuesta, String nuevaDescripcion, double nuevaCuota) {
        apuesta.setDescripcion(nuevaDescripcion);
        apuesta.setCuota(nuevaCuota);
        System.out.println("Nueva Apuesta: " + nuevaDescripcion + ", Nueva cuota: " + nuevaCuota);
    }
}
