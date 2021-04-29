package es.unican.Test;

import javax.persistence.Entity;

@Entity
public class SerieComenzada extends Serie{

	private int ultimaTemporadaVista;
	
	protected SerieComenzada() {}
	
	public SerieComenzada(String titulo, float precio, long ID_Serie, TipoDeSerie tds, String sinopsis, int utv) {
		super(titulo, precio, ID_Serie, tds, sinopsis);
		setUltimaTemporadaVista(utv);
	}

	public int getUltimaTemporadaVista() {
		return ultimaTemporadaVista;
	}

	public void setUltimaTemporadaVista(int ultimaTemporadaVista) {
		this.ultimaTemporadaVista = ultimaTemporadaVista;
	}

}
