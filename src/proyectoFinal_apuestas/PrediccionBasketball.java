package proyectoFinal_apuestas;

import java.io.*;
import java.util.ArrayList;

public class PrediccionBasketball extends Prediccion {
	private int ganador, puntos1, puntos2, triples1, triples2, faltas1, faltas2;

	@Override
	public String toString() {
		return monto + ", " + ganador + ", " + puntos1 + ", " + puntos2 + ", " + triples1 + ", " + triples2 + ", " + faltas1 + ", " + faltas2;
	}
	
	public static PrediccionBasketball leerPrediccion(String cadena) {
		String[] datos = cadena.split(", ");
    	return new PrediccionBasketball(Double.parseDouble(datos[0]), Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), Integer.parseInt(datos[6]), Integer.parseInt(datos[7]));
	}

	public PrediccionBasketball(double monto, int ganador, int puntos1, int puntos2, int triples1, int triples2,
			int faltas1, int faltas2) {
		super(monto);
		this.ganador = ganador;
		this.puntos1 = puntos1;
		this.puntos2 = puntos2;
		this.triples1 = triples1;
		this.triples2 = triples2;
		this.faltas1 = faltas1;
		this.faltas2 = faltas2;
	}

	public PrediccionBasketball(double monto, int ganador) {
		super(monto);
		this.ganador=ganador;
	}
	
	public PrediccionBasketball(double monto, int ganador, int puntos1, int puntos2) {
    	super(monto);
    	this.ganador = ganador;
    	this.puntos1 = puntos1;
    	this.puntos2 = puntos2;
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

	public int getGanador() {
		return ganador;
	}

	public void setGanador(int ganador) {
		this.ganador = ganador;
	}

	public boolean registrarPrediccionesBasketballTxt(String archivo) {
		try {
			PrintWriter escritor = new PrintWriter(new FileWriter(archivo, true));
			String registro = toString();
			escritor.println(registro);
			escritor.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	static ArrayList<PrediccionBasketball> leerPrediccionesBasketballTxt(String archivo) {
		ArrayList<PrediccionBasketball> prediccionBasketball = new ArrayList<>();
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea;
			while ((linea = lector.readLine()) != null) {
				prediccionBasketball.add(PrediccionBasketball.leerPrediccion(linea));
			}
			lector.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
		return prediccionBasketball;
	}

	public boolean registrarPrediccionesBasketballBin(String archivo) {
		try (DataOutputStream escritor = new DataOutputStream(new FileOutputStream(archivo, true))) {
			escritor.writeDouble(this.monto);
			escritor.writeInt(this.ganador);
			escritor.writeInt(this.puntos1);
			escritor.writeInt(this.puntos2);
			escritor.writeInt(this.triples1);
			escritor.writeInt(this.triples2);
			escritor.writeInt(this.faltas1);
			escritor.writeInt(this.faltas2);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	static ArrayList<PrediccionBasketball> leerPrediccionesBasketballBin(String archivo) {
		ArrayList<PrediccionBasketball> prediccionBasketball = new ArrayList<>();
		try (DataInputStream lector = new DataInputStream(new FileInputStream(archivo))) {
			while (lector.available() != 0) {
				double monto = lector.readDouble();
				int ganador = lector.readInt();
				int puntos1 = lector.readInt();
				int puntos2 = lector.readInt();
				int triples1 = lector.readInt();
				int triples2 = lector.readInt();
				int faltas1 = lector.readInt();
				int faltas2 = lector.readInt();

				prediccionBasketball.add(new PrediccionBasketball(monto, ganador, puntos1, puntos2, triples1, triples2, faltas1, faltas2));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
		return prediccionBasketball;
	}

	static boolean reescribirPrediccionesBasketballTxt(ArrayList<PrediccionBasketball> prediccionBasketball, String archivo) {
		try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
			for (PrediccionBasketball pb : prediccionBasketball) {
				String registro = pb.toString();
				escritor.println(registro);
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	static boolean reescribirPrediccionesBasketballBin(ArrayList<PrediccionBasketball> prediccionBasketball, String archivo) {
		try (DataOutputStream escritor = new DataOutputStream(new FileOutputStream(archivo))) {
			for (PrediccionBasketball pb : prediccionBasketball) {
				escritor.writeDouble(pb.getMonto());
				escritor.writeInt(pb.getGanador());
				escritor.writeInt(pb.getPuntos1());
				escritor.writeInt(pb.getPuntos2());
				escritor.writeInt(pb.getTriples1());
				escritor.writeInt(pb.getTriples2());
				escritor.writeInt(pb.getFaltas1());
				escritor.writeInt(pb.getFaltas2());
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
