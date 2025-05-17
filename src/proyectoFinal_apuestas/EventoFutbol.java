package proyectoFinal_apuestas;

public class EventoFutbol extends Evento{
	private String equipo1, equipo2;
	private int goles1, goles2, amarillas1, amarillas2, rojas1, rojas2;
	
	
	
	@Override
	public String toString() {
		return getFecha().getYear()+", "+getFecha().getMonthValue()+", "+getFecha().getDayOfMonth()+", "+equipo1+", "+equipo2+", "+goles1+", "+goles2+", "+amarillas1+", "+amarillas2+", "+rojas1+", "+rojas2;
	}
	
	public static EventoFutbol leerEventFutbol(String cadena) {
		String[] datos = cadena.split(", ");
		return new EventoFutbol(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), datos[3], datos[4], Integer.parseInt(datos[5]), Integer.parseInt(datos[6]), Integer.parseInt(datos[7]), Integer.parseInt(datos[8]), Integer.parseInt(datos[9]), Integer.parseInt(datos[10]));
	}
	
	


	public EventoFutbol(int anio, int mes, int dia, String equipo1, String equipo2, int goles1, int goles2,
			int amarillas1, int amarillas2, int rojas1, int rojas2) {
		super(anio, mes, dia);
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.goles1 = goles1;
		this.goles2 = goles2;
		this.amarillas1 = amarillas1;
		this.amarillas2 = amarillas2;
		this.rojas1 = rojas1;
		this.rojas2 = rojas2;
	}

	public EventoFutbol(int anio, int mes, int dia, String eq1, String eq2) {
		super(anio, mes, dia);
		this.equipo1 = eq1;
		this.equipo2 = eq2;
		this.goles1 = 0;
		this.goles2 = 0;
		this.amarillas1 = 0;
		this.amarillas2 = 0;
		this.rojas1 = 0;
		this.rojas2 = 0;
	}

	
	public void terminarEvento(String result) {
		String[] datos = result.split(", ");
		this.goles1 = Integer.parseInt(datos[0]);
		this.goles2 = Integer.parseInt(datos[1]);
		this.amarillas1 = Integer.parseInt(datos[2]);
		this.amarillas2 = Integer.parseInt(datos[3]);
		this.rojas1 = Integer.parseInt(datos[4]);
		this.rojas2 = Integer.parseInt(datos[5]);
	}
	

	public int getAmarillas1() {
		return amarillas1;
	}

	public void setAmarillas1(int amarillas1) {
		this.amarillas1 = amarillas1;
	}

	public int getAmarillas2() {
		return amarillas2;
	}

	public void setAmarillas2(int amarillas2) {
		this.amarillas2 = amarillas2;
	}

	public int getRojas1() {
		return rojas1;
	}

	public void setRojas1(int rojas1) {
		this.rojas1 = rojas1;
	}

	public int getRojas2() {
		return rojas2;
	}

	public void setRojas2(int rojas2) {
		this.rojas2 = rojas2;
	}

	public String getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(String equipo1) {
		this.equipo1 = equipo1;
	}

	public String getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(String equipo2) {
		this.equipo2 = equipo2;
	}

	public int getGoles1() {
		return goles1;
	}

	public void setGoles1(int goles1) {
		this.goles1 = goles1;
	}

	public int getGoles2() {
		return goles2;
	}

	public void setGoles2(int goles2) {
		this.goles2 = goles2;
	}
	
	public int ganador() {
		if (goles1>goles2) {
			return 1;
		}else if (goles2>goles1) {
			return 2;
		}else {
			return 0;
		}
	}

}