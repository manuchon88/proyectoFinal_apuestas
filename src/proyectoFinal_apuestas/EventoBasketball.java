package proyectoFinal_apuestas;

public class EventoBasketball extends Evento {
	private String equipo1, equipo2;
	private int puntos1, puntos2, triples1, triples2, faltas1, faltas2;
	
	
	
	@Override
	public String toString() {
		return getFecha().getYear()+", "+getFecha().getMonthValue()+", "+getFecha().getDayOfMonth()+", "+equipo1+", "+equipo2+", "+puntos1+", "+puntos2+", "+triples1+", "+triples2+", "+faltas1+", "+faltas2;
	}
	
	public static EventoBasketball leerEventBasketball(String cadena) {
		String[] datos = cadena.split(", ");
		return new EventoBasketball(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), datos[3], datos[4], Integer.parseInt(datos[5]), Integer.parseInt(datos[6]), Integer.parseInt(datos[7]), Integer.parseInt(datos[8]), Integer.parseInt(datos[9]), Integer.parseInt(datos[10]));
	}


	public EventoBasketball(int anio, int mes, int dia, String equipo1, String equipo2) {
		super(anio, mes, dia);
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.puntos1 = 0;
		this.puntos2 = 0;
		this.triples1 = 0;
		this.triples2 = 0;
		this.faltas1 = 0;
		this.faltas2 = 0;
	}
	

	
	public EventoBasketball(int anio, int mes, int dia, String equipo1, String equipo2, int puntos1, int puntos2,
			int triples1, int triples2, int faltas1, int faltas2) {
		super(anio, mes, dia);
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.puntos1 = puntos1;
		this.puntos2 = puntos2;
		this.triples1 = triples1;
		this.triples2 = triples2;
		this.faltas1 = faltas1;
		this.faltas2 = faltas2;
	}


	public void terminarEvento(String result) {
		String[] datos = result.split(", ");
		this.puntos1 = Integer.parseInt(datos[0]);
		this.puntos2 = Integer.parseInt(datos[1]);
		this.triples1 = Integer.parseInt(datos[2]);
		this.triples2 = Integer.parseInt(datos[3]);
		this.faltas1 = Integer.parseInt(datos[4]);
		this.faltas2 = Integer.parseInt(datos[5]);
	}

	public int totalFaltas() {
		return triples1+triples2;
	}
	
	public int totalTriples() {
		return faltas1+faltas2;
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

	public int getPuntos1() {
		return puntos1;
	}

	public void setPuntos1(int puntos1) {
		this.puntos1 = puntos1;
	}

	public int getPuntos2() {
		return puntos2;
	}

	public void setPuntos2(int puntos2) {
		this.puntos2 = puntos2;
	}

	public int getTriples1() {
		return triples1;
	}

	public void setTriples1(int triples1) {
		this.triples1 = triples1;
	}

	public int getTriples2() {
		return triples2;
	}

	public void setTriples2(int triples2) {
		this.triples2 = triples2;
	}

	public int getFaltas1() {
		return faltas1;
	}

	public void setFaltas1(int faltas1) {
		this.faltas1 = faltas1;
	}

	public int getFaltas2() {
		return faltas2;
	}

	public void setFaltas2(int faltas2) {
		this.faltas2 = faltas2;
	}


	
	
	
	
	
	
}
