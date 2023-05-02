package examen.model;

public class Examen {
	private Integer idExamen;
	private Integer idClase;
	private Integer numPreguntas;
	private String fechaRealizacion;
	
	
	public Examen(Integer idClase, Integer numPreguntas, String fechaRealizacion) {
		super();
		this.idClase = idClase;
		this.numPreguntas = numPreguntas;
		this.fechaRealizacion = fechaRealizacion;
	}
	
	public Integer getIdExamen() {
		return idExamen;
	}
	public void setIdExamen(Integer idExamen) {
		this.idExamen = idExamen;
	}
	public Integer getIdClase() {
		return idClase;
	}
	public void setIdClase(Integer idClase) {
		this.idClase = idClase;
	}
	public Integer getNumPreguntas() {
		return numPreguntas;
	}
	public void setNumPreguntas(Integer numPreguntas) {
		this.numPreguntas = numPreguntas;
	}
	public String getFechaRealizacion() {
		return fechaRealizacion;
	}
	public void setFechaRealizacion(String fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
}
