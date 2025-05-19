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

	public ApuestaBasketball(String descripcion, double cuota, PrediccionBasketball prediccion, EventoBasketball evento) {
		super(descripcion, cuota);
		this.predict = prediccion;
		this.event = evento;
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
		if (predict==null) {
			return descripcion + "#" + cuota + "#" + "null" + "#" + event.toString()+"#"+ tipo;
			
		} else {
			return descripcion + "#" + cuota + "#" + predict.toString() + "#" + event.toString()+"#"+ tipo;

		}
	}

	public static ApuestaBasketball leerApuestaBasket(String cadena) {
		String[] datos = cadena.split("#");
		return new ApuestaBasketball(datos[0], Double.parseDouble(datos[1]), PrediccionBasketball.leerPrediccion(datos[2]),
			EventoBasketball.leerEventBasketball(datos[3]));
	}

	@Override
	public boolean isGanador(int tipo) {
		switch (tipo) {
			case 1:
				return predict.getGanador() == event.ganador();
			case 2:
				return predict.getPuntos1() == event.getPuntos1() && predict.getPuntos2() == event.getPuntos2();
			case 3:
				return predict.getPuntos1() == event.getPuntos1()
					&& predict.getPuntos2() == event.getPuntos2()
					&& predict.getTriples1() == event.getTriples1()
					&& predict.getFaltas1() == event.getFaltas1()
					&& predict.getTriples2() == event.getTriples2()
					&& predict.getFaltas2() == event.getFaltas2();
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
				String[] datos = linea.split("#");
				String descripcion = datos[0];
				double cuota = Double.parseDouble(datos[1]);

				double monto = Double.parseDouble(datos[2]);
				int ganador = Integer.parseInt(datos[3]);
				int puntos1 = Integer.parseInt(datos[4]);
				int puntos2 = Integer.parseInt(datos[5]);
				int triples1 = Integer.parseInt(datos[6]);
				int triples2 = Integer.parseInt(datos[7]);
				int faltas1 = Integer.parseInt(datos[8]);
				int faltas2 = Integer.parseInt(datos[9]);

				int anio = Integer.parseInt(datos[10]);
				int mes = Integer.parseInt(datos[11]);
				int dia = Integer.parseInt(datos[12]);
				String equipo1 = datos[13];
				String equipo2 = datos[14];
				int puntos_1 = Integer.parseInt(datos[15]);
				int puntos_2 = Integer.parseInt(datos[16]);
				int triples_1 = Integer.parseInt(datos[17]);
				int triples_2 = Integer.parseInt(datos[18]);
				int faltas_1 = Integer.parseInt(datos[19]);
				int faltas_2 = Integer.parseInt(datos[20]);

				PrediccionBasketball prediccion = new PrediccionBasketball(monto, ganador, puntos1, puntos2,
						triples1, triples2, faltas1, faltas2);
				EventoBasketball evento = new EventoBasketball(anio, mes, dia, equipo1, equipo2, puntos_1, puntos_2,
						triples_1, triples_2, faltas_1, faltas_2);

				apuestaBasketball.add(new ApuestaBasketball(descripcion, cuota, prediccion, evento));
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

	static ArrayList<ApuestaBasketball> leerApuestaBasketballBin(String archivo) {
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
	}

	static boolean reescribirApuestaBasketballTxt(ArrayList<ApuestaBasketball> apuestaBasketball, String archivo) {
		try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
			for (ApuestaBasketball ab : apuestaBasketball) {
				String registro = ab.getDescripcion() + "#" + ab.getCuota() + "#" +
						ab.getPredict().getMonto() + "#" + ab.getPredict().getGanador() + "#" +
						ab.getPredict().getPuntos1() + "#" + ab.getPredict().getPuntos2() + "#" +
						ab.getPredict().getTriples1() + "#" + ab.getPredict().getTriples2() + "#" +
						ab.getPredict().getFaltas1() + "#" + ab.getPredict().getFaltas2() + "#" +
						ab.getEvent().getFecha().getYear() + "#" + ab.getEvent().getFecha().getMonthValue() + "#" +
						ab.getEvent().getFecha().getDayOfMonth() + "#" + ab.getEvent().getEquipo1() + "#" +
						ab.getEvent().getEquipo2() + "#" + ab.getEvent().getPuntos1() + "#" +
						ab.getEvent().getPuntos2() + "#" + ab.getEvent().getTriples1() + "#" +
						ab.getEvent().getTriples2() + "#" + ab.getEvent().getFaltas1() + "#" +
						ab.getEvent().getFaltas2();
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
