package proyectoFinal_apuestas;

public class ResultadoBasketball {
	private int puntos1, puntos2, triples1, triples2, faltas1, faltas2;

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

	public ResultadoBasketball(int puntos1, int puntos2, int triples1, int triples2, int faltas1, int faltas2) {
		super();
		this.puntos1 = puntos1;
		this.puntos2 = puntos2;
		this.triples1 = triples1;
		this.triples2 = triples2;
		this.faltas1 = faltas1;
		this.faltas2 = faltas2;
	}

	@Override
	public String toString() {
		return puntos1+", "+puntos2+", "+triples1+", "+triples2+", "+faltas1+", "+faltas2;
	}

}