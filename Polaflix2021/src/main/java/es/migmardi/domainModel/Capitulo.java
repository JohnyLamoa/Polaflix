package es.migmardi.domainModel;

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
	private boolean visto;
	
	protected Capitulo() {}
	
	public Capitulo(int ndc, String desc) {
		numeroDeCapitulo=ndc;
		descripcion=desc;
		visto=false;
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

	public boolean getVisto() {
		return visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}
}
