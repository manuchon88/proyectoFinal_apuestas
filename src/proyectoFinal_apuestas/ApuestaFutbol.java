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
	private int tipoApuesta;
	
	
    public int getTipoApuesta() {
		return tipoApuesta;
	}



	public void setTipoApuesta(int tipoApuesta) {
		this.tipoApuesta = tipoApuesta;
	}

	public ApuestaFutbol(String descripcion, double cuota, EventoFutbol evento, int tipo) {
		super(descripcion, cuota);
		this.event = evento;
		this.tipoApuesta = tipo;
	}

	public ApuestaFutbol(String descripcion, double cuota, EventoFutbol evento, int tipo, PrediccionFutbol prediccion) {
        super(descripcion, cuota);
        this.event = evento;
        this.tipoApuesta=tipo;
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
		return descripcion+"#"+cuota+"#"+event+"#"+tipoApuesta+"#"+predict;
	}



	public static ApuestaFutbol leerApuestaFut(String cadena) {
		String[] datos = cadena.split("#");
		if (datos[4].equals("null")) {
			return new ApuestaFutbol(datos[0], Double.parseDouble(datos[1]), EventoFutbol.leerToEvFutbol(datos[2]), Integer.parseInt(datos[3]));
		} else {
			return new ApuestaFutbol(datos[0], Double.parseDouble(datos[1]), EventoFutbol.leerToEvFutbol(datos[2]), Integer.parseInt(datos[3]), PrediccionFutbol.leerPrediccion(datos[4]));
		}
	}

	@Override
    public boolean isGanador() {
    	switch (tipoApuesta) {
		case 1: {
			if (predict.getGanador()==event.ganador()) {
				return true;
			} else {
				return false;
			}
		}case 2:{
			if (predict.getGanador()==event.ganador()&&predict.getGoles1()==event.getGoles1() && predict.getGoles2()==event.getGoles2()) {
				return true;
			}else {
				return false;
			}
		}case 3:{
			if (predict.getGanador()==event.ganador()&&predict.getGoles1()==event.getGoles1() && predict.getGoles2()==event.getGoles2() &&
					predict.getAmarillas1()==event.getAmarillas1()&&predict.getRojas1()==event.getRojas1()&&
					predict.getAmarillas2()==event.getAmarillas2()&&predict.getRojas2()==event.getRojas2()) {
				return true;
			}else {
				return false;
			}
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + tipoApuesta);
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
				apuestaFutbol.add(ApuestaFutbol.leerApuestaFut(linea));
				
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
	
	/*static ArrayList<ApuestaFutbol> leerApuestaFutbolBin(String archivo){
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
	}*/
	
	static boolean reescribirApuestaFutbolTxt(ArrayList<ApuestaFutbol> apuestaFutbol, String archivo) {
		try {
			PrintWriter escritor = new PrintWriter(new FileWriter(archivo));
			for (ApuestaFutbol af : apuestaFutbol) {
				String registro = af.toString();
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
