package proyectoFinal_apuestas;

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

    // ¿Para que es el registrar? 
    public void registrar() {
        System.out.println("¿Para que es el registrar?");
    }

    public void cerrarSesion() {
        System.out.println("Cerrar sesión");
    }

    public void iniciarSesion() {
        System.out.println("Iniciar Sesión");
    }
	
	
}
