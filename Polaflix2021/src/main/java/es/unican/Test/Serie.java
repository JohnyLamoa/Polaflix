package es.unican.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Serie {
	private String titulo;
	private float precio;
	//@OneToOne
	private TipoDeSerie tipoDeSerie;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ID_Serie;
	private String sinopsis;
	@OneToOne(cascade=CascadeType.ALL)
	private HistorialPrecios hp;
	@OneToMany(cascade=CascadeType.ALL)
	private Map<Integer, Temporada> temporadasDeLaSerie;
	@OneToMany(cascade=CascadeType.DETACH)
	private Set<Actor> actoresPrincipales;
	@OneToMany(cascade=CascadeType.DETACH)
	private Set<Direccion> directores;

	protected Serie() {}
	
	public Serie (String titulo, float precio, long ID_Serie, TipoDeSerie tds, String sinopsis){
		this.setTitulo(titulo);
		this.setPrecio(precio);
		this.setTipoDeSerie(tds);
		this.ID_Serie=ID_Serie;
		this.setSinopsis(sinopsis);
		
		hp = new HistorialPrecios();
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(System.currentTimeMillis());
		hp.setPrecioAtTime(now, precio);
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public TipoDeSerie getTipoDeSerie() {
		return tipoDeSerie;
	}
	
	public void setTipoDeSerie(TipoDeSerie tipoDeSerie) {
		this.tipoDeSerie = tipoDeSerie;
	}
	
	public long getID_Serie() {
		return ID_Serie;
	}
	
	public String getSinopsis() {
		return sinopsis;
	}
	
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	
	public Set<Actor> getActoresPrincipales() {
		return actoresPrincipales;
	}
	
	public void setActoresPrincipales(Set<Actor> actoresPrincipales) {
		this.actoresPrincipales = actoresPrincipales;
	}
	
	public Set<Direccion> getDirectores() {
		return directores;
	}
	
	public void setDirectores(Set<Direccion> directores) {
		this.directores = directores;
	}

	public Temporada getTemporadaDeLaSerie(int numeroDeTemporada) {
		return temporadasDeLaSerie.get(numeroDeTemporada);
	}

	public void setTemporadasDeLaSerie(Temporada temporadaDeLaSerie, int numeroDeTemporada) {
		temporadasDeLaSerie.put(numeroDeTemporada, temporadaDeLaSerie);
	}

	public Calendar getPreviousPriceChangeDate(Calendar currentDate) {
		ArrayList<Calendar> fechas=hp.getDatesFromHistorialPrecios();
		Calendar higherDate=fechas.get(0);
		for(Calendar date:fechas) {
			if(currentDate.after(date) & date.before(higherDate)) {
				higherDate=date;
			}
		}
		return higherDate;
	}

	public float getPriceAtTime(Calendar date) {
		return hp.getPrecioAtTime(date);
	}


}
