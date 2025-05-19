package proyectoFinal_apuestas;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrediccionFutbol extends Prediccion {
	private int ganador, goles1, goles2, amarillas1, amarillas2, rojas1, rojas2;
	
    @Override
	public String toString() {
		return monto+", "+ganador+", "+goles1+", "+goles2+", "+amarillas1+", "+amarillas2+", "+rojas1+", "+rojas2;
	}
    
    public static PrediccionFutbol leerPrediccion(String cadena) {
		String[] datos = cadena.split(", ");
    	return new PrediccionFutbol(Double.parseDouble(datos[0]), Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), Integer.parseInt(datos[4]), Integer.parseInt(datos[5]), Integer.parseInt(datos[6]), Integer.parseInt(datos[7]));
	}
    
    public PrediccionFutbol(double monto, int ganador, int goles1, int goles2, int amarillas1, int amarillas2, 
    		int rojas1, int rojas2) {
		super(monto);
		this.ganador = ganador;
		this.goles1 = goles1;
		this.goles2 = goles2;
		this.amarillas1 = amarillas1;
		this.amarillas2 = amarillas2;
		this.rojas1 = rojas1;
		this.rojas2 = rojas2;
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

	public int getGanador() {
		return ganador;
	}

	public void setGanador(int ganador) {
		this.ganador = ganador;
	}

	
	public boolean registrarPrediccionesFutbolTxt(String archivo) {
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
	
	static ArrayList<PrediccionFutbol> leerPrediccionesFutbolTxt (String archivo){
		ArrayList<PrediccionFutbol> prediccionFutbol = new ArrayList<PrediccionFutbol>();
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea;
			while ((linea = lector.readLine())!=null) {
				String [] datos = linea.split(", ");
				double monto = Double.parseDouble(datos[0]);
				int ganador = Integer.parseInt(datos[1]);
				int goles1 = Integer.parseInt(datos[2]);
				int goles2 = Integer.parseInt(datos[3]); 
				int amarillas1 = Integer.parseInt(datos[4]);
				int amarillas2 = Integer.parseInt(datos[5]);
				int rojas1 = Integer.parseInt(datos[6]);
				int rojas2 = Integer.parseInt(datos[7]);
				
				prediccionFutbol.add(new PrediccionFutbol(monto, ganador, goles1, goles2, amarillas1, amarillas2, rojas1, rojas2));
			}
			lector.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		}catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
	
		return prediccionFutbol;
	}
	
	public boolean registrarPrediccionesFutbolBin(String archivo) {
		try {	
			DataOutputStream escritor =  new DataOutputStream(new FileOutputStream(archivo,true));
			escritor.writeDouble(this.monto);
			escritor.writeInt(this.ganador);
			escritor.writeInt(this.goles1);
			escritor.writeInt(this.goles2);
			escritor.writeInt(this.amarillas1);
			escritor.writeInt(this.amarillas2);
			escritor.writeInt(this.rojas1);
			escritor.writeInt(this.rojas2);
			escritor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	
	static ArrayList<PrediccionFutbol> leerPrediccionesFutbolBin(String archivo){
		ArrayList<PrediccionFutbol> prediccionFutbol =  new ArrayList<PrediccionFutbol>();
		
		try {
			DataInputStream lector =  new DataInputStream(new FileInputStream(archivo));
			while(lector.available()!=0) {
				double monto = lector.readDouble();
				int ganador = lector.readInt();
				int goles1 = lector.readInt();
				int goles2 = lector.readInt(); 
				int amarillas1 = lector.readInt();
				int amarillas2 = lector.readInt();
				int rojas1 = lector.readInt();
				int rojas2 = lector.readInt();
				
				prediccionFutbol.add(new PrediccionFutbol(monto, ganador, goles1, goles2,
						 amarillas1, amarillas2, rojas1, rojas2));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		}catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
		return prediccionFutbol;
	}
	
	static boolean reescribirPrediccionesFutbolTxt(ArrayList<PrediccionFutbol> prediccionFutbol, String archivo) {
		try {
			PrintWriter escritor = new PrintWriter(new FileWriter(archivo));
			for (PrediccionFutbol pf : prediccionFutbol) {
				String registro = pf.getMonto() + ", " + pf.getGanador() + ", " +
						pf.getGoles1() + ", " + pf.getGoles2() + ", " + pf.getAmarillas1() + pf.getAmarillas2() + ", " +
						pf.getRojas1() + ", " + pf.getRojas2();
				escritor.println(registro);
			}
			escritor.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	static boolean reescribirPrediccionesFutbolBin(ArrayList<PrediccionFutbol> prediccionFutbol, String archivo) {
		try {
			DataOutputStream escritor =  new DataOutputStream(new FileOutputStream(archivo));
			for (PrediccionFutbol pf : prediccionFutbol) {
				escritor.writeDouble(pf.getMonto());
				escritor.writeInt(pf.getGanador());
				escritor.writeInt(pf.getGoles1());
				escritor.writeInt(pf.getGoles2());
				escritor.writeInt(pf.getAmarillas1());
				escritor.writeInt(pf.getAmarillas2());
				escritor.writeInt(pf.getRojas1());
				escritor.writeInt(pf.getRojas2());
			}
			escritor.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
    
}