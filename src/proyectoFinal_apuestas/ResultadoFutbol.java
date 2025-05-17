package proyectoFinal_apuestas;

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

}