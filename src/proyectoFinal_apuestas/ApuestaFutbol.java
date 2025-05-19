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
		return descripcion+", "+cuota+", "+predict.toString()+", "+event.toString();
	}



	public static ApuestaFutbol leerApuestaFut(String cadena) {
		String[] datos = cadena.split(", ");
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
	
	public boolean registrarApuestaFutbolTxt(String archivo) {
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
	
	static ArrayList<ApuestaFutbol> leerApuestaFutbolTxt (String archivo){
		ArrayList<ApuestaFutbol> apuestaFutbol = new ArrayList<ApuestaFutbol>();
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea;
			while ((linea = lector.readLine())!=null) {
				String [] datos = linea.split(", ");
				String descripcion = datos[0];
				double cuota = Double.parseDouble(datos[1]);
				
				double monto = Double.parseDouble(datos[2]);
				int ganador = Integer.parseInt(datos[3]);
				int goles1 = Integer.parseInt(datos[4]);
				int goles2 = Integer.parseInt(datos[5]); 
				int amarillas1 = Integer.parseInt(datos[6]);
				int amarillas2 = Integer.parseInt(datos[7]);
				int rojas1 = Integer.parseInt(datos[8]);
				int rojas2 = Integer.parseInt(datos[9]);
				
				int anio = Integer.parseInt(datos[10]);
				int mes = Integer.parseInt(datos[11]); 
				int dia = Integer.parseInt(datos[12]);
				String equipo1 = datos[13];
				String equipo2 =datos[14];
				int goles_1 = Integer.parseInt(datos[15]);
				int goles_2 = Integer.parseInt(datos[16]); 
				int amarillas_1 = Integer.parseInt(datos[17]);
				int amarillas_2 = Integer.parseInt(datos[18]);
				int rojas_1 = Integer.parseInt(datos[19]);
				int rojas_2 = Integer.parseInt(datos[20]);
				
				
				PrediccionFutbol prediccion = new PrediccionFutbol(monto, ganador, goles1, goles2,
						 amarillas1, amarillas2, rojas1, rojas2);
				EventoFutbol evento = new EventoFutbol(anio, mes, dia, equipo1, equipo2, goles_1, goles_2,
						amarillas_1, amarillas_2, rojas_1, rojas_2);
				
				apuestaFutbol.add(new ApuestaFutbol(descripcion, cuota, prediccion, evento));
			}
			lector.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		}catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
	
		return apuestaFutbol;
	}
	
	public boolean registrarApuestaFutbolBin(String archivo) {
		try {	
			DataOutputStream escritor =  new DataOutputStream(new FileOutputStream(archivo,true));
			escritor.writeUTF(this.descripcion);
			escritor.writeDouble(this.cuota);

			escritor.writeDouble(this.getPredict().getMonto());
			escritor.writeInt(this.getPredict().getGanador());
			escritor.writeInt(this.getPredict().getGoles1());
			escritor.writeInt(this.getPredict().getGoles2());
			escritor.writeInt(this.getPredict().getAmarillas1());
			escritor.writeInt(this.getPredict().getAmarillas2());
			escritor.writeInt(this.getPredict().getRojas1());
			escritor.writeInt(this.getPredict().getRojas2());
			
			escritor.writeInt(this.getEvent().getFecha().getYear());
			escritor.writeInt(this.getEvent().getFecha().getMonthValue());
			escritor.writeInt(this.getEvent().getFecha().getDayOfMonth());
			escritor.writeUTF(this.getEvent().getEquipo1());
			escritor.writeUTF(this.getEvent().getEquipo2());
			escritor.writeInt(this.getEvent().getGoles1());
			escritor.writeInt(this.getEvent().getGoles2());
			escritor.writeInt(this.getEvent().getAmarillas1());
			escritor.writeInt(this.getEvent().getAmarillas2());
			escritor.writeInt(this.getEvent().getRojas1());
			escritor.writeInt(this.getEvent().getRojas2());
			escritor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	
	static ArrayList<ApuestaFutbol> leerApuestaFutbolBin(String archivo){
		ArrayList<ApuestaFutbol> apuestaFutbol =  new ArrayList<ApuestaFutbol>();
		
		try {
			DataInputStream lector =  new DataInputStream(new FileInputStream(archivo));
			while(lector.available()!=0) {
				String descripcion = lector.readUTF();
				double cuota = lector.readDouble();

				double monto = lector.readDouble();
				int ganador = lector.readInt();
				int goles1 = lector.readInt();
				int goles2 = lector.readInt(); 
				int amarillas1 = lector.readInt();
				int amarillas2 = lector.readInt();
				int rojas1 = lector.readInt();
				int rojas2 = lector.readInt();
				
				int anio = lector.readInt();
				int mes = lector.readInt(); 
				int dia = lector.readInt();
				String equipo1 = lector.readUTF();
				String equipo2 = lector.readUTF();
				int goles_1 = lector.readInt();
				int goles_2 = lector.readInt(); 
				int amarillas_1 = lector.readInt();
				int amarillas_2 = lector.readInt();
				int rojas_1 = lector.readInt();
				int rojas_2 = lector.readInt();
				
				PrediccionFutbol prediccion = new PrediccionFutbol(monto, ganador, goles1, goles2,
						 amarillas1, amarillas2, rojas1, rojas2);
				EventoFutbol evento = new EventoFutbol(anio, mes, dia, equipo1, equipo2, goles_1, goles_2,
						amarillas_1, amarillas_2, rojas_1, rojas_2);
				
				apuestaFutbol.add(new ApuestaFutbol(descripcion, cuota, prediccion, evento));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		}catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
		return apuestaFutbol;
	}
	
	static boolean reescribirApuestaFutbolTxt(ArrayList<ApuestaFutbol> apuestaFutbol, String archivo) {
		try {
			PrintWriter escritor = new PrintWriter(new FileWriter(archivo));
			for (ApuestaFutbol af : apuestaFutbol) {
				String registro = af.getDescripcion() + ", " + af.getCuota() + ", " +
						af.getPredict().getMonto() + ", " + af.getPredict().getGanador() + ", " +
						af.getPredict().getGoles1() + ", " + af.getPredict().getGoles2() + ", " +
						af.getPredict().getAmarillas1() + ", " + af.getPredict().getAmarillas2() + ", " +
						af.getPredict().getRojas1() + ", " + af.getPredict().getRojas2() + ", " +
						af.getEvent().getFecha().getYear() + ", " + af.getEvent().getFecha().getMonthValue() + ", " +
						af.getEvent().getFecha().getDayOfMonth() + ", " + af.getEvent().getEquipo1() + ", " +
						af.getEvent().getEquipo2() + ", " + af.getEvent().getGoles1() + ", " +
						af.getEvent().getGoles2() + ", " + af.getEvent().getAmarillas1() + ", " +
						af.getEvent().getAmarillas2() + ", " + af.getEvent().getRojas1() + ", " +
						af.getEvent().getRojas2();
				escritor.println(registro);
			}
			escritor.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	static boolean reescribirApuestaFutbolBin(ArrayList<ApuestaFutbol> apuestaFutbol, String archivo) {
		try {
			DataOutputStream escritor =  new DataOutputStream(new FileOutputStream(archivo));
			for (ApuestaFutbol af : apuestaFutbol) {
				escritor.writeUTF(af.getDescripcion());
				escritor.writeDouble(af.getCuota());

				escritor.writeDouble(af.getPredict().getMonto());
				escritor.writeInt(af.getPredict().getGanador());
				escritor.writeInt(af.getPredict().getGoles1());
				escritor.writeInt(af.getPredict().getGoles2());
				escritor.writeInt(af.getPredict().getAmarillas1());
				escritor.writeInt(af.getPredict().getAmarillas2());
				escritor.writeInt(af.getPredict().getRojas1());
				escritor.writeInt(af.getPredict().getRojas2());

				escritor.writeInt(af.getEvent().getFecha().getYear());
				escritor.writeInt(af.getEvent().getFecha().getMonthValue());
				escritor.writeInt(af.getEvent().getFecha().getDayOfMonth());
				escritor.writeUTF(af.getEvent().getEquipo1());
				escritor.writeUTF(af.getEvent().getEquipo2());
				escritor.writeInt(af.getEvent().getGoles1());
				escritor.writeInt(af.getEvent().getGoles2());
				escritor.writeInt(af.getEvent().getAmarillas1());
				escritor.writeInt(af.getEvent().getAmarillas2());
				escritor.writeInt(af.getEvent().getRojas1());
				escritor.writeInt(af.getEvent().getRojas2());
			}
			escritor.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
