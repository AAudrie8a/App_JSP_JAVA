package alumno.model;

public class Alumno {
	private Integer carnet;	
	private String nombre;
	private String apellido;
	
	public Alumno () {
		
	}
	public Alumno(Integer carnet, String nombre, String apellido) {
		this.carnet = carnet;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public Alumno(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public Integer getCarnet() {
		return carnet;
	}

	public void setCarnet(Integer carnet) {
		this.carnet = carnet;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	
	
	

}
