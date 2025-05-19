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

public class Administrador extends Usuario {

	protected Administrador(String nombre, String correo, String contrasenia) {
		super(nombre, correo, contrasenia);
	}

	public String crearEvento(int deporte, String datos) {
		switch (deporte) {
		case 1: {
			return EventoFutbol.leerEventFutbol(datos).toString();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + deporte);
		}
    }
	
	public String crearApuesta(int deporte, String apuestaString) {
		switch (deporte) {
		case 1: {
			return ApuestaFutbol.leerApuestaFut(apuestaString).toString();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + deporte);
		}
    }
	
	public String editarApuesta(int deporte, String CadenaApuesta, String nuevaDescripcion, double nuevaCuota) {
        switch (deporte) {
		case 1: {
			ApuestaFutbol apuesta = ApuestaFutbol.leerApuestaFut(CadenaApuesta);
			apuesta.setDescripcion(nuevaDescripcion);
			apuesta.setCuota(nuevaCuota);
			return apuesta.toString();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + deporte);
		}
    }
	
	
	@Override
	public String toString() {
		return  getNombre() + ", " + getCorreo() + ", " + getContrasenia();
	}

	public static int VerificarInicioSesion(String correoIngresado, String contraseniaIngresada) {
    	ArrayList<Administrador> base = leerAdministradoresTxt(Archivos.archivosAdministradores);
    	for (Administrador user : base) {
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
	
	public static Administrador iniciarSesion(String correoIngresado, String contraseniaIngresada) {
    	ArrayList<Administrador> base = leerAdministradoresTxt(Archivos.archivosAdministradores);
		for (Administrador admin : base) {
			if (admin.getCorreo().equals(correoIngresado) && admin.getContrasenia().equals(contraseniaIngresada)) {
				return admin;
			}
		}
		return null;
	}
	
	public boolean registrarAdministradoresTxt(String archivo) {
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
	
	static ArrayList<Administrador> leerAdministradoresTxt (String archivo){
		ArrayList<Administrador> administradores = new ArrayList<Administrador>();
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivo));
			String linea;
			while ((linea = lector.readLine())!=null) {
				String [] datos = linea.split(", ");
				String nombre = datos[0];
				String correo = datos[1];
				String contrasenia = datos[2];
				
				administradores.add(new Administrador(nombre, correo, contrasenia));
			}
			lector.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		}catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
	
		return administradores;
	}
	
	public boolean registrarAdministradoresBin(String archivo) {
		try {	
			DataOutputStream escritor =  new DataOutputStream(new FileOutputStream(archivo,true));
			escritor.writeUTF(getNombre());
			escritor.writeUTF(getCorreo());
			escritor.writeUTF(getContrasenia());
			escritor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	
	static ArrayList<Administrador> leerAdministradoresBin(String archivo){
		ArrayList<Administrador> administradores =  new ArrayList<Administrador>();
		
		try {
			DataInputStream lector =  new DataInputStream(new FileInputStream(archivo));
			while(lector.available()!=0) {
				String nombre = lector.readUTF();
				String correo = lector.readUTF();
				String contrasenia = lector.readUTF();
				
				administradores.add(new Administrador(nombre, correo, contrasenia));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Ha ocurrido un error al encontrar el archivo");
		}catch (IOException e) {
			System.out.println("Ha ocurrido un error al recibir los datos");
		}
		return administradores;
	}
	
	static boolean reescribirAdministradoresTxt(ArrayList<Administrador> administradores, String archivo) {
		try {
			PrintWriter escritor = new PrintWriter(new FileWriter(archivo));
			for (Administrador aa : administradores) {
				String registro = aa.getNombre() + ", " +  aa.getCorreo() + ", " + aa.getContrasenia();
				escritor.println(registro);
			}
			escritor.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	static boolean reescribirAdministradoresBin(ArrayList<Administrador> administradores, String archivo) {
		try {
			DataOutputStream escritor =  new DataOutputStream(new FileOutputStream(archivo));
			for (Administrador aa : administradores) {
				escritor.writeUTF(aa.getNombre());
				escritor.writeUTF(aa.getCorreo());
				escritor.writeUTF(aa.getContrasenia());
			}
			escritor.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
