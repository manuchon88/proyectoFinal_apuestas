package proyectoFinal_apuestas;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ResultadoBasketball {
	private int puntos1, puntos2, triples1, triples2, faltas1, faltas2;

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

	public ResultadoBasketball(int puntos1, int puntos2, int triples1, int triples2, int faltas1, int faltas2) {
		super();
		this.puntos1 = puntos1;
		this.puntos2 = puntos2;
		this.triples1 = triples1;
		this.triples2 = triples2;
		this.faltas1 = faltas1;
		this.faltas2 = faltas2;
	}

	@Override
	public String toString() {
		return puntos1+", "+puntos2+", "+triples1+", "+triples2+", "+faltas1+", "+faltas2;
	}
	
	public boolean registrarResultadosBasketballTxt(String archivo) {
		try {
			PrintWriter escritor = new PrintWriter(new FileWriter(archivo,true));
			String registro = toString();
			escritor.println(registro);
			escritor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}	
		return true;
    }

	public static ArrayList<ResultadoBasketball> leerResultadosBasketballTxt(String archivo) {
		ArrayList<ResultadoBasketball> resultadosBasketball = new ArrayList<>();
		try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = lector.readLine()) != null) {
				String[] datos = linea.split(", ");
				int puntos1 = Integer.parseInt(datos[0]);
				int puntos2 = Integer.parseInt(datos[1]);
				int triples1 = Integer.parseInt(datos[2]);
				int triples2 = Integer.parseInt(datos[3]);
				int faltas1 = Integer.parseInt(datos[4]);
				int faltas2 = Integer.parseInt(datos[5]);
				resultadosBasketball.add(new ResultadoBasketball(puntos1, puntos2, triples1, triples2, faltas1, faltas2));
			}
		} catch (IOException e) {
			System.out.println("Error");
		}
		return resultadosBasketball;
	}

	public boolean registrarResultadosBasketballBin(String archivo) {
		try (DataOutputStream escritor = new DataOutputStream(new FileOutputStream(archivo, true))) {
			escritor.writeInt(puntos1);
			escritor.writeInt(puntos2);
			escritor.writeInt(triples1);
			escritor.writeInt(triples2);
			escritor.writeInt(faltas1);
			escritor.writeInt(faltas2);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public static ArrayList<ResultadoBasketball> leerResultadosBasketballBin(String archivo) {
	    ArrayList<ResultadoBasketball> resultadosBasketball = new ArrayList<ResultadoBasketball>();
	    try (DataInputStream lector = new DataInputStream(new FileInputStream(archivo))) {
	        while (lector.available() > 0) {
	            int puntos1 = lector.readInt();
	            int puntos2 = lector.readInt();
	            int triples1 = lector.readInt();
	            int triples2 = lector.readInt();
	            int faltas1 = lector.readInt();
	            int faltas2 = lector.readInt();
	            resultadosBasketball.add(new ResultadoBasketball(puntos1, puntos2, triples1, triples2, faltas1, faltas2));
	        }
	    } catch (IOException e) {
	        System.out.println("Error");
	    }
	    return resultadosBasketball;
	}


	public static boolean reescribirResultadosBasketballTxt(ArrayList<ResultadoBasketball> resultadosBasketball, String archivo) {
		try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
			for (ResultadoBasketball r : resultadosBasketball) {
				String registro = r.getPuntos1() + ", " + r.getPuntos2() + ", " + r.getTriples1() +
						", " + r.getTriples2() + ", " + r.getFaltas1() + ", " + r.getFaltas2();
				escritor.println(registro);
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public static boolean reescribirResultadosBasketballBin(ArrayList<ResultadoBasketball> resultados, String archivo) {
		try (DataOutputStream escritor = new DataOutputStream(new FileOutputStream(archivo))) {
			for (ResultadoBasketball r : resultados) {
				escritor.writeInt(r.getPuntos1());
				escritor.writeInt(r.getPuntos2());
				escritor.writeInt(r.getTriples1());
				escritor.writeInt(r.getTriples2());
				escritor.writeInt(r.getFaltas1());
				escritor.writeInt(r.getFaltas2());
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}