package proyectoFinal_apuestas;

public abstract class Apuesta {
    protected String descripcion;
    protected double cuota;

    public Apuesta(String descripcion, double cuota) {
        this.descripcion = descripcion;
        this.cuota = cuota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

	
	public abstract boolean isGanador(int tipo);
}
