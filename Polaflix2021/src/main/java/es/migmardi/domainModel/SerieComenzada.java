package es.migmardi.domainModel;

import javax.persistence.Entity;

@Entity
public class SerieComenzada extends Serie{

	private int ultimaTemporadaVista;
	private int ultimoCapituloVisto;
	
	protected SerieComenzada() {}
	
	public SerieComenzada(String titulo, float precio, long ID_Serie, TipoDeSerie tds, String sinopsis, int utv, int ucv) {
		super(titulo, precio, ID_Serie, tds, sinopsis);
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

}
