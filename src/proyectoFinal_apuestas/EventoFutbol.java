package proyectoFinal_apuestas;
import java.io.*;
import java.util.ArrayList;

public class EventoFutbol extends Evento{
	private String equipo1, equipo2;
	private int torneo, goles1, goles2, amarillas1, amarillas2, rojas1, rojas2;
	private boolean terminado;
	
	
	
	@Override
	public String toString() {
		return getFecha().getYear()+", "+getFecha().getMonthValue()+", "+getFecha().getDayOfMonth()+", "+equipo1+", "+equipo2+", "+torneo+", "+goles1+", "+goles2+", "+amarillas1+", "+amarillas2+", "+rojas1+", "+rojas2+", "+ terminado;
	}
	
	public static EventoFutbol leerToEvFutbol(String cadena) {
		String[] datos = cadena.split(", ");
		return new EventoFutbol(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), Integer.parseInt(datos[2]), datos[3], datos[4], Integer.parseInt(datos[5]), Integer.parseInt(datos[6]), Integer.parseInt(datos[7]), Integer.parseInt(datos[8]), Integer.parseInt(datos[9]), Integer.parseInt(datos[10]), Integer.parseInt(datos[11]), Boolean.parseBoolean(datos[12]));
	}
	
	


	public EventoFutbol(int anio, int mes, int dia, String equipo1, String equipo2, int torneo, int goles1, int goles2,
			int amarillas1, int amarillas2, int rojas1, int rojas2, boolean terminado) {
		super(anio, mes, dia);
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.torneo = torneo;
		this.goles1 = goles1;
		this.goles2 = goles2;
		this.amarillas1 = amarillas1;
		this.amarillas2 = amarillas2;
		this.rojas1 = rojas1;
		this.rojas2 = rojas2;
		this.terminado = terminado;
	}

	public EventoFutbol(int anio, int mes, int dia, String eq1, String eq2, int torneo) {
		super(anio, mes, dia);
		this.equipo1 = eq1;
		this.equipo2 = eq2;
		this.torneo = torneo;
		this.goles1 = 0;
		this.goles2 = 0;
		this.amarillas1 = 0;
		this.amarillas2 = 0;
		this.rojas1 = 0;
		this.rojas2 = 0;
		this.terminado = false;
	}
	
	
	
	public int getTorneo() {
		return torneo;
	}

	public void setTorneo(int torneo) {
		this.torneo = torneo;
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
	
	public boolean registrarEventosFutbolTxt(String archivo) {
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
	
	static ArrayList<EventoFutbol> leerEventosFutbolTxt (String archivo){
		ArrayList<EventoFutbol> eventoFutbol = new ArrayList<EventoFutbol>();
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea;
			while ((linea = lector.readLine())!=null) {
				eventoFutbol.add(EventoFutbol.leerToEvFutbol(linea));
				
				/*String [] datos = linea.split(", ");
				int anio = Integer.parseInt(datos[0]);
				int mes = Integer.parseInt(datos[1]); 
				int dia = Integer.parseInt(datos[2]);
				String equipo1 = datos[3];
				String equipo2 =datos[4];
				int torneo = Integer.parseInt(datos[5]);
				int goles1 = Integer.parseInt(datos[6]);
				int goles2 = Integer.parseInt(datos[7]); 
				int amarillas1 = Integer.parseInt(datos[8]);
				int amarillas2 = Integer.parseInt(datos[9]);
				int rojas1 = Integer.parseInt(datos[10]);
				int rojas2 = Integer.parseInt(datos[11]);
				
				eventoFutbol.add(new EventoFutbol(anio, mes, dia, equipo1, equipo2, torneo, goles1, goles2,
					 amarillas1, amarillas2, rojas1, rojas2));*/
			}
			lector.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		}catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
	
		return eventoFutbol;
	}
	
	public boolean registrarEventosFutbolBin(String archivo) {
		try {	
			DataOutputStream escritor =  new DataOutputStream(new FileOutputStream(archivo,true));
			escritor.writeInt(getFecha().getYear());
			escritor.writeInt(getFecha().getMonthValue());
			escritor.writeInt(getFecha().getDayOfMonth());
			escritor.writeUTF(this.equipo1);
			escritor.writeUTF(this.equipo2);
			escritor.writeInt(this.torneo);
			escritor.writeInt(this.goles1);
			escritor.writeInt(this.goles2);
			escritor.writeInt(this.amarillas1);
			escritor.writeInt(this.amarillas2);
			escritor.writeInt(this.rojas1);
			escritor.writeInt(this.rojas2);
			escritor.writeBoolean(this.terminado);
			escritor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	
	static ArrayList<EventoFutbol> leerEventosFutbolBin(String archivo){
		ArrayList<EventoFutbol> eventoFutbol =  new ArrayList<EventoFutbol>();
		
		try {
			DataInputStream lector =  new DataInputStream(new FileInputStream(archivo));
			while(lector.available()!=0) {
				int anio = lector.readInt();
				int mes = lector.readInt(); 
				int dia = lector.readInt();
				String equipo1 = lector.readUTF();
				String equipo2 = lector.readUTF();
				int torneo = lector.readInt();
				int goles1 = lector.readInt();
				int goles2 = lector.readInt(); 
				int amarillas1 = lector.readInt();
				int amarillas2 = lector.readInt();
				int rojas1 = lector.readInt();
				int rojas2 = lector.readInt();
				Boolean term = lector.readBoolean();
				
				eventoFutbol.add(new EventoFutbol(anio, mes, dia, equipo1, equipo2, torneo, goles1, goles2,
						 amarillas1, amarillas2, rojas1, rojas2, term));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		}catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
		return eventoFutbol;
	}
	
	static boolean reescribirEventosFutbolTxt(ArrayList<EventoFutbol> eventosFutbol, String archivo) {
		try {
			PrintWriter escritor = new PrintWriter(new FileWriter(archivo));
			for (EventoFutbol ef : eventosFutbol) {
				String registro = ef.toString();
				escritor.println(registro);
			}
			escritor.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	static boolean reescribirEventosFutbolBin(ArrayList<EventoFutbol> eventosFutbol, String archivo) {
		try {
			DataOutputStream escritor =  new DataOutputStream(new FileOutputStream(archivo));
			for (EventoFutbol ef : eventosFutbol) {
				escritor.writeInt(ef.getFecha().getYear());
				escritor.writeInt(ef.getFecha().getMonthValue());
				escritor.writeInt(ef.getFecha().getDayOfMonth());
				escritor.writeUTF(ef.getEquipo1());
				escritor.writeUTF(ef.getEquipo2());
				escritor.writeInt(ef.getGoles1());
				escritor.writeInt(ef.getGoles2());
				escritor.writeInt(ef.getAmarillas1());
				escritor.writeInt(ef.getAmarillas2());
				escritor.writeInt(ef.getRojas1());
				escritor.writeInt(ef.getRojas2());
			}
			escritor.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}
	
	

}