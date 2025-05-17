package proyectoFinal_apuestas;

public class ApuestaFutbol extends Apuesta {
	private PrediccionFutbol predict;
	private EventoFutbol event;
    public ApuestaFutbol(String descripcion, double cuota, PrediccionFutbol prediccion, EventoFutbol evento) {
        super(descripcion, cuota);
        this.event = evento;
        this.predict = prediccion;
    }
    
    
    
    public PrediccionFutbol getPredict() {
		return predict;
	}



	public void setPredict(PrediccionFutbol predict) {
		this.predict = predict;
	}



	public EventoFutbol getEvent() {
		return event;
	}



	public void setEvent(EventoFutbol event) {
		this.event = event;
	}



	@Override
	public String toString() {
		return descripcion+"~"+cuota+"~"+predict.toString()+"~"+event.toString();
	}



	public static ApuestaFutbol leerApuestaFut(String cadena) {
		String[] datos = cadena.split("~");
		return new ApuestaFutbol(datos[0], Double.parseDouble(datos[1]), PrediccionFutbol.leerPrediccion(datos[2]), EventoFutbol.leerEventFutbol(datos[3]));
	}

	@Override
    public boolean isGanador(int tipo) {
    	switch (tipo) {
		case 1: {
			if (predict.getGanador()==event.ganador()) {
				return true;
			} else {
				return false;
			}
		}case 2:{
			if (predict.getGoles1()==event.getGoles1() && predict.getGoles2()==event.getGoles2()) {
				return true;
			}else {
				return false;
			}
		}case 3:{
			if (predict.getGoles1()==event.getGoles1() && predict.getGoles2()==event.getGoles2() &&
					predict.getAmarillas1()==event.getAmarillas1()&&predict.getRojas1()==event.getRojas1()&&
					predict.getAmarillas2()==event.getAmarillas2()&&predict.getRojas2()==event.getRojas2()) {
				return true;
			}else {
				return false;
			}
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + tipo);
		}
    }
}
