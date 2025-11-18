package proyectoFinal_apuestas;

import java.time.*;

public abstract class Evento {
	private LocalDate fecha;
	
	public Evento(int anio, int mes, int dia) {
		this.fecha = LocalDate.of(anio, mes, dia);
	}
	
	
	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	

	//public abstract void terminarEvento(String result);
}
