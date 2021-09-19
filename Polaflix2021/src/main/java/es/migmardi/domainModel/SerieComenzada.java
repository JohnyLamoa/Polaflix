package es.migmardi.domainModel;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class SerieComenzada extends Serie{

	private int ultimaTemporadaVista;
	private int ultimoCapituloVisto;
	@OneToOne
	private Serie serie;
	protected SerieComenzada() {}
	
	public SerieComenzada(Serie serie, int utv, int ucv) {
		this.serie=serie;
		setUltimaTemporadaVista(utv);
		setUltimoCapituloVisto(ucv);
	}

	public int getUltimaTemporadaVista() {
		return ultimaTemporadaVista;
	}

	public void setUltimaTemporadaVista(int ultimaTemporadaVista) {
		this.ultimaTemporadaVista = ultimaTemporadaVista;
	}
	
	public int getUltimoCapituloVisto() {
		return ultimoCapituloVisto;
	}

	public void setUltimoCapituloVisto(int ultimoCapituloVisto) {
		this.ultimoCapituloVisto = ultimoCapituloVisto;
	}

	public Serie getSerie() {
		return serie;
	}

}
