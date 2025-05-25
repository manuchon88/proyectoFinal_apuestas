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
	
	public int ganador() {
		if (puntos1>puntos2) {
			return 1;
		}else if (puntos2>puntos1) {
			return 2;
		}else {
			return 0;
		}
	}


	public boolean registrarEventoBasketballTxt(String archivo) {
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
	
	static ArrayList<EventoBasketball> leerEventosBasketballTxt (String archivo){
		ArrayList<EventoBasketball> eventoBasketball = new ArrayList<EventoBasketball>();
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea;
			while ((linea = lector.readLine())!=null) {
				String [] datos = linea.split(", ");
				int anio = Integer.parseInt(datos[0]);
				int mes = Integer.parseInt(datos[1]); 
				int dia = Integer.parseInt(datos[2]);
				String equipo1 = datos[3];
				String equipo2 =datos[4];
				int puntos1 = Integer.parseInt(datos[5]);
				int puntos2 = Integer.parseInt(datos[6]); 
				int triples1 = Integer.parseInt(datos[7]);
				int triples2 = Integer.parseInt(datos[8]);
				int faltas1 = Integer.parseInt(datos[9]);
				int faltas2 = Integer.parseInt(datos[10]);
				
				eventoBasketball.add(new EventoBasketball(anio, mes, dia, equipo1, equipo2, puntos1, puntos2,
						triples1, triples2, faltas1, faltas2));
			}
			lector.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		}catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
	
		return eventoBasketball;
	}
	
	public boolean registrarEventosBasketballBin(String archivo) {
		try {	
			DataOutputStream escritor =  new DataOutputStream(new FileOutputStream(archivo,true));
			escritor.writeInt(getFecha().getYear());
			escritor.writeInt(getFecha().getMonthValue());
			escritor.writeInt(getFecha().getDayOfMonth());
			escritor.writeUTF(this.equipo1);
			escritor.writeUTF(this.equipo2);
			escritor.writeInt(this.puntos1);
			escritor.writeInt(this.puntos2);
			escritor.writeInt(this.triples1);
			escritor.writeInt(this.triples2);
			escritor.writeInt(this.faltas1);
			escritor.writeInt(this.faltas2);
			escritor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	
	static ArrayList<EventoBasketball> leerRecintosBin(String archivo){
		ArrayList<EventoBasketball> eventoBasketball =  new ArrayList<EventoBasketball>();
		
		try {
			DataInputStream lector =  new DataInputStream(new FileInputStream(archivo));
			while(lector.available()!=0) {
				int anio = lector.readInt();
				int mes = lector.readInt(); 
				int dia = lector.readInt();
				String equipo1 = lector.readUTF();
				String equipo2 = lector.readUTF();
				int puntos1 = lector.readInt();
				int puntos2 = lector.readInt(); 
				int triples1 = lector.readInt();
				int triples2 = lector.readInt();
				int faltas1 = lector.readInt();
				int faltas2 = lector.readInt();
				
				eventoBasketball.add(new EventoBasketball(anio, mes, dia, equipo1, equipo2, puntos1, puntos2,
						triples1, triples2, faltas1, faltas2));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		}catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
		return eventoBasketball;
	}
	
	static boolean reescribirEventosBasketballTxt(ArrayList<EventoBasketball> eventosBasketball, String archivo) {
		try {
			PrintWriter escritor = new PrintWriter(new FileWriter(archivo));
			for (EventoBasketball eb : eventosBasketball) {
				String registro = eb.toString();
				escritor.println(registro);
			}
			escritor.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	static boolean reescribirEventosFutbolBin(ArrayList<EventoBasketball> eventosBasketball, String archivo) {
		try {
			DataOutputStream escritor =  new DataOutputStream(new FileOutputStream(archivo));
			for (EventoBasketball eb : eventosBasketball) {
				escritor.writeInt(eb.getFecha().getYear());
				escritor.writeInt(eb.getFecha().getMonthValue());
				escritor.writeInt(eb.getFecha().getDayOfMonth());
				escritor.writeUTF(eb.getEquipo1());
				escritor.writeUTF(eb.getEquipo2());
				escritor.writeInt(eb.getPuntos1());
				escritor.writeInt(eb.getPuntos2());
				escritor.writeInt(eb.getTriples1());
				escritor.writeInt(eb.getTriples2());
				escritor.writeInt(eb.getFaltas1());
				escritor.writeInt(eb.getFaltas2());
			}
			escritor.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	
	
	
	
}
