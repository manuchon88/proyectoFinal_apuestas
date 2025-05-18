package proyectoFinal_apuestas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Apostador extends Usuario{
	private double saldo;
	private ArrayList<ApuestaFutbol> historialApuestasFutbol = new ArrayList<>();

	public Apostador(String nombre, String correo, String contrasenia) {
		super(nombre, correo, contrasenia);
		this.saldo = 0;
	}
	
	public Apostador(String nombre, String correo, String contrasenia, double saldo) {
		super(nombre, correo, contrasenia);
		this.saldo = saldo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String crearPrediccion(int deporte, String cadenaPredict) {
		//1 futbol, 2 basket
		switch (deporte) {
		case 1: {
			return PrediccionFutbol.leerPrediccion(cadenaPredict).toString();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + deporte);
		}
		
    }

    public void apostar(int deporte, String apuesta) {
        switch (deporte) {
		case 1: {
			ApuestaFutbol bet = ApuestaFutbol.leerApuestaFut(apuesta);
			double monto = bet.getPredict().getMonto();
			if (saldo >= monto) {
				saldo -= monto;
				historialApuestasFutbol.add(new ApuestaFutbol(bet.getDescripcion(), bet.getCuota(), bet.getPredict(), bet.getEvent()));
				System.out.println("Apuesta realizada. Monto: " + monto);
				System.out.println("Saldo restante: " + saldo);
			} else {
				System.out.println("No se tiene el saldo necesario para apostar. Saldo disponible: " + saldo);
			}
			
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + deporte);
		}
    }

    public void verHistorial() {
        System.out.println("Historial de apuestas:");
        if (historialApuestasFutbol.isEmpty()) {
            System.out.println("No hay apuestas registradas.");
        } else {
            for (ApuestaFutbol a : historialApuestasFutbol) {
                System.out.println("Descripcion: " + a.getDescripcion() + ", Evento: " + a.getEvent());
            }
        }
    }

    @Override
	public String toString() {
		return getNombre()+", "+getCorreo()+", "+getContrasenia()+", "+saldo;
	}

	// ¿Para que es el registrar? 
    public boolean registrarse() {
    	try {
			PrintWriter escritor = new PrintWriter(new FileWriter(Archivos.archivosUsuarios, true));
			escritor.println(this.toString());
			escritor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	return true;
    }

    public boolean cerrarSesion() {
    	ArrayList<Apostador> base = leerApostadoresTxt();
    	for (Apostador user : base) {
			if (user.getCorreo().equals(this.getCorreo())) {
				user.setContrasenia(this.getContrasenia());
				user.setNombre(this.getNombre());
				user.setSaldo(this.getSaldo());
			}
		}
    	if (reescribirApostadoresTxt(base)) {
			return true;
		} else {
			return false;
		}
    }

    public static int VerificarInicioSesion(String correoIngresado, String contraseniaIngresada) {
    	ArrayList<Apostador> base = leerApostadoresTxt();
    	for (Apostador user : base) {
			if (user.getCorreo().equals(correoIngresado)) {
				if (user.getContrasenia().equals(contraseniaIngresada)) {
					return 2; //2 = Usuario y contraseña correctos
				} else {
					return 1; //1 = Usuario existente, contraseña incorrecta
				}
			}
		}
    	return 0; //0 = Usuario no existe, tiene que crear cuenta
    }
	
    public static Apostador iniciarSesion(String correoIngresado, String contraseniaIngresada) {
    	ArrayList<Apostador> base = leerApostadoresTxt();
		for (Apostador apos : base) {
			if (apos.getCorreo().equals(correoIngresado) && apos.getContrasenia().equals(contraseniaIngresada)) {
				return apos;
			}
		}
		return null;
	}
    
	public static ArrayList<Apostador> leerApostadoresTxt() {
		ArrayList<Apostador> base = new ArrayList<Apostador>();
		try {
			BufferedReader lector = new BufferedReader(new FileReader(Archivos.archivosUsuarios));
			String linea;
			while ((linea = lector.readLine())!=null) {
				String datos[] = linea.split(", ");
				base.add(new Apostador(datos[0], datos[1], datos[2], Double.parseDouble(datos[3])));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base;
	}
	
	public static boolean reescribirApostadoresTxt(ArrayList<Apostador> apostadores) {
		try {
			PrintWriter escritor = new PrintWriter(new FileWriter(Archivos.archivosUsuarios));
			for (Apostador apos : apostadores) {
				escritor.println(apos.toString());
			}
			escritor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
