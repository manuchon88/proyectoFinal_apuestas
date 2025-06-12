package proyectoFinal_apuestas;

import java.io.*;
import java.util.ArrayList;

public class ApuestaBasketball extends Apuesta {
	private PrediccionBasketball predict;
	private EventoBasketball event;
	private int tipo;

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public ApuestaBasketball(String descripcion, double cuota, EventoBasketball evento, int tipo) {
		super(descripcion, cuota);
		this.event = evento;
		this.tipo = tipo;
	}
	
	public ApuestaBasketball(String descripcion, double cuota, EventoBasketball evento, int tipo, PrediccionBasketball prediccion) {
		super(descripcion, cuota);
		this.predict = prediccion;
		this.event = evento;
		this.tipo = tipo;
	}

	public PrediccionBasketball getPredict() {
		return predict;
	}

	public void setPredict(PrediccionBasketball predict) {
		this.predict = predict;
	}

	public EventoBasketball getEvent() {
		return event;
	}

	public void setEvent(EventoBasketball event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return descripcion + "#" + cuota + "#" + event+"#"+ tipo + "#" + predict;
	}

	public static ApuestaBasketball leerApuestaBasket(String cadena) {
		String[] datos = cadena.split("#");
		if (datos[4].equals("null")) {
			return new ApuestaBasketball(datos[0], Double.parseDouble(datos[1]), EventoBasketball.leerEventBasketball(datos[2]), Integer.parseInt(datos[3]));
		} else {
			return new ApuestaBasketball(datos[0], Double.parseDouble(datos[1]), EventoBasketball.leerEventBasketball(datos[2]), Integer.parseInt(datos[3]), PrediccionBasketball.leerPrediccion(datos[4]));
		}
	}

	@Override
	public boolean isGanador() {
		switch (tipo) {
		case 1: {
			if (predict.getGanador()==event.ganador()) {
				return true;
			} else {
				return false;
			}
		}case 2:{
			if (predict.getGanador()==event.ganador()&&predict.getPuntos1()==event.getPuntos1() && predict.getPuntos2()==event.getPuntos2()) {
				return true;
			}else {
				return false;
			}
		}case 3:{
			if (predict.getGanador()==event.ganador()&&predict.getPuntos1()==event.getPuntos1() && predict.getPuntos2()==event.getPuntos2() &&
					predict.getTriples1()==event.getTriples1()&&predict.getFaltas1()==event.getFaltas1()&&
					predict.getTriples2()==event.getTriples2()&&predict.getFaltas2()==event.getFaltas2()) {
				return true;
			}else {
				return false;
			}
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + tipo);
		}
	}

	public boolean registrarApuestaBasketballTxt(String archivo) {
		try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo, true))) {
			escritor.println(toString());
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	static ArrayList<ApuestaBasketball> leerApuestaBasketballTxt(String archivo) {
		ArrayList<ApuestaBasketball> apuestaBasketball = new ArrayList<>();
		try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				apuestaBasketball.add(ApuestaBasketball.leerApuestaBasket(linea));
				
			}
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
		return apuestaBasketball;
	}

	public boolean registrarApuestaBasketballBin(String archivo) {
		try (DataOutputStream escritor = new DataOutputStream(new FileOutputStream(archivo, true))) {
			escritor.writeUTF(this.descripcion);
			escritor.writeDouble(this.cuota);

			escritor.writeDouble(predict.getMonto());
			escritor.writeInt(predict.getGanador());
			escritor.writeInt(predict.getPuntos1());
			escritor.writeInt(predict.getPuntos2());
			escritor.writeInt(predict.getTriples1());
			escritor.writeInt(predict.getTriples2());
			escritor.writeInt(predict.getFaltas1());
			escritor.writeInt(predict.getFaltas2());

			escritor.writeInt(event.getFecha().getYear());
			escritor.writeInt(event.getFecha().getMonthValue());
			escritor.writeInt(event.getFecha().getDayOfMonth());
			escritor.writeUTF(event.getEquipo1());
			escritor.writeUTF(event.getEquipo2());
			escritor.writeInt(event.getPuntos1());
			escritor.writeInt(event.getPuntos2());
			escritor.writeInt(event.getTriples1());
			escritor.writeInt(event.getTriples2());
			escritor.writeInt(event.getFaltas1());
			escritor.writeInt(event.getFaltas2());
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/*static ArrayList<ApuestaBasketball> leerApuestaBasketballBin(String archivo) {
		ArrayList<ApuestaBasketball> apuestaBasketball = new ArrayList<>();
		try (DataInputStream lector = new DataInputStream(new FileInputStream(archivo))) {
			while (lector.available() != 0) {
				String descripcion = lector.readUTF();
				double cuota = lector.readDouble();

				double monto = lector.readDouble();
				int ganador = lector.readInt();
				int puntos1 = lector.readInt();
				int puntos2 = lector.readInt();
				int triples1 = lector.readInt();
				int triples2 = lector.readInt();
				int faltas1 = lector.readInt();
				int faltas2 = lector.readInt();

				int anio = lector.readInt();
				int mes = lector.readInt();
				int dia = lector.readInt();
				String equipo1 = lector.readUTF();
				String equipo2 = lector.readUTF();
				int puntos_1 = lector.readInt();
				int puntos_2 = lector.readInt();
				int triples_1 = lector.readInt();
				int triples_2 = lector.readInt();
				int faltas_1 = lector.readInt();
				int faltas_2 = lector.readInt();

				PrediccionBasketball pred = new PrediccionBasketball(monto, ganador, puntos1, puntos2, triples1, triples2, faltas1, faltas2);
				EventoBasketball evento = new EventoBasketball(anio, mes, dia, equipo1, equipo2, puntos_1, puntos_2, triples_1, triples_2, faltas_1, faltas_2);
				apuestaBasketball.add(new ApuestaBasketball(descripcion, cuota, pred, evento));
			}
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
		return apuestaBasketball;
	}*/

	static boolean reescribirApuestaBasketballTxt(ArrayList<ApuestaBasketball> apuestaBasketball, String archivo) {
		try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
			for (ApuestaBasketball ab : apuestaBasketball) {
				String registro = ab.toString();
				escritor.println(registro);
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	static boolean reescribirApuestaBasketballBin(ArrayList<ApuestaBasketball> apuestaBasketball, String archivo) {
		try (DataOutputStream escritor = new DataOutputStream(new FileOutputStream(archivo))) {
			for (ApuestaBasketball ab : apuestaBasketball) {
				escritor.writeUTF(ab.getDescripcion());
				escritor.writeDouble(ab.getCuota());

				escritor.writeDouble(ab.getPredict().getMonto());
				escritor.writeInt(ab.getPredict().getGanador());
				escritor.writeInt(ab.getPredict().getPuntos1());
				escritor.writeInt(ab.getPredict().getPuntos2());
				escritor.writeInt(ab.getPredict().getTriples1());
				escritor.writeInt(ab.getPredict().getTriples2());
				escritor.writeInt(ab.getPredict().getFaltas1());
				escritor.writeInt(ab.getPredict().getFaltas2());

				escritor.writeInt(ab.getEvent().getFecha().getYear());
				escritor.writeInt(ab.getEvent().getFecha().getMonthValue());
				escritor.writeInt(ab.getEvent().getFecha().getDayOfMonth());
				escritor.writeUTF(ab.getEvent().getEquipo1());
				escritor.writeUTF(ab.getEvent().getEquipo2());
				escritor.writeInt(ab.getEvent().getPuntos1());
				escritor.writeInt(ab.getEvent().getPuntos2());
				escritor.writeInt(ab.getEvent().getTriples1());
				escritor.writeInt(ab.getEvent().getTriples2());
				escritor.writeInt(ab.getEvent().getFaltas1());
				escritor.writeInt(ab.getEvent().getFaltas2());
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
