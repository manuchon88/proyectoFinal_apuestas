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

public class ResultadoFutbol {
    private int goles1, goles2, amarillas1, amarillas2, rojas1, rojas2;

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

	@Override
	public String toString() {
		return goles1+", "+goles2+", "+amarillas1+", "+amarillas2+", "+rojas1+", "+rojas2;
	}

	public ResultadoFutbol(int goles1, int goles2, int amarillas1, int amarillas2, int rojas1, int rojas2) {
		super();
		this.goles1 = goles1;
		this.goles2 = goles2;
		this.amarillas1 = amarillas1;
		this.amarillas2 = amarillas2;
		this.rojas1 = rojas1;
		this.rojas2 = rojas2;
	}
	
	public boolean registrarResultadosFutbolTxt(String archivo) {
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

    public static ArrayList<ResultadoFutbol> leerResultadosFutbolTxt(String archivo) {
        ArrayList<ResultadoFutbol> resultadosFutbol = new ArrayList<ResultadoFutbol>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine())!=null) {
                String[] datos = linea.split(", ");
                int goles1 = Integer.parseInt(datos[0]);
                int goles2 = Integer.parseInt(datos[1]);
                int amarillas1 = Integer.parseInt(datos[2]);
                int amarillas2 = Integer.parseInt(datos[3]);
                int rojas1 = Integer.parseInt(datos[4]);
                int rojas2 = Integer.parseInt(datos[5]);
                resultadosFutbol.add(new ResultadoFutbol(goles1, goles2, amarillas1, amarillas2, rojas1, rojas2));
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
        return resultadosFutbol;
    }

    public boolean registrarResultadosFutbolBin(String archivo) {
        try {
        	DataOutputStream escritor = new DataOutputStream(new FileOutputStream(archivo, true));
            escritor.writeInt(goles1);
            escritor.writeInt(goles2);
            escritor.writeInt(amarillas1);
            escritor.writeInt(amarillas2);
            escritor.writeInt(rojas1);
            escritor.writeInt(rojas2);
        } catch (IOException e) {
			// TODO Auto-generated catch block
            return false;
        }
        return true;
    }

    public static boolean reescribirResultadosFutbolTxt(ArrayList<ResultadoFutbol> resultados, String archivo) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
            for (ResultadoFutbol r : resultados) {
                String registro = r.getGoles1() + ", " + r.getGoles2() + ", " + r.getAmarillas1() + ", " +
                		r.getAmarillas2() + ", " + r.getRojas1() + ", " + r.getRojas2();
                escritor.println(registro);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    
    public static boolean reescribirResultadosFutbolBin(ArrayList<ResultadoFutbol> resultadosFutbol, String archivo) {
        try (DataOutputStream escritor = new DataOutputStream(new FileOutputStream(archivo))) {
            for (ResultadoFutbol r : resultadosFutbol) {
                escritor.writeInt(r.getGoles1());
                escritor.writeInt(r.getGoles2());
                escritor.writeInt(r.getAmarillas1());
                escritor.writeInt(r.getAmarillas2());
                escritor.writeInt(r.getRojas1());
                escritor.writeInt(r.getRojas2());
            }
			escritor.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
        
}