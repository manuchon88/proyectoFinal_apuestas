package proyectoFinal_apuestas;

public abstract class Usuario {
	private String nombre, correo, contrasenia;
	
	public Usuario(String nombre, String correo, String contrasenia){
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
}