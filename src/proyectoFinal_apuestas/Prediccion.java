package proyectoFinal_apuestas;

public abstract class Prediccion {
    protected double monto;

    public Prediccion(double monto) {
        this.monto = monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    
}
