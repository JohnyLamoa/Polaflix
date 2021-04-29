package es.unican.Test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Capitulo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idCapitulo;
	private int numeroDeCapitulo;
	private String descripcion;
	
	protected Capitulo() {}
	
	public Capitulo(int ndc, String desc) {
		numeroDeCapitulo=ndc;
		descripcion=desc;
	}
	
	public int getNumeroDeCapitulo() {
		return numeroDeCapitulo;
	}
	public void setNumeroDeCapitulo(int numeroDeCapitulo) {
		this.numeroDeCapitulo = numeroDeCapitulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
