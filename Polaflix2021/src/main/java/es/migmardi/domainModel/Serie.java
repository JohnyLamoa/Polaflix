package es.migmardi.domainModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import es.migmardi.service.api.Views.DescripcionSerie;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Serie {
	
	
	@JsonView(DescripcionSerie.class)
	private String titulo;
	
	@JsonView(DescripcionSerie.class)
	private float precio;
	
	@JsonView(DescripcionSerie.class)
	//@OneToOne
	private TipoDeSerie tipoDeSerie;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ID_Serie;
	
	@JsonView(DescripcionSerie.class)
	private String sinopsis;
	
	@JsonView(DescripcionSerie.class)
	@OneToMany(cascade=CascadeType.ALL)
	private List<Temporada> temporadasDeLaSerie;
	
	@JsonView(DescripcionSerie.class)
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Actor> actoresPrincipales;
	
	@JsonView(DescripcionSerie.class)
	@OneToMany(cascade=CascadeType.DETACH)
	private Set<Direccion> directores;
	
	@ElementCollection
	private Map<Calendar, Float> mapHistorialPrecios;

	protected Serie() {}
	
	public Serie (long ID_serie, String titulo, float precio, TipoDeSerie tds, String sinopsis){
		this.ID_Serie=ID_serie;
		this.setTitulo(titulo);
		this.setPrecio(precio);
		this.setTipoDeSerie(tds);
		this.setSinopsis(sinopsis);
		mapHistorialPrecios=new HashMap<Calendar, Float>();
		
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(System.currentTimeMillis());
		mapHistorialPrecios.put(now, precio);
	}


	public Serie(Serie serie) {
		this.actoresPrincipales=serie.actoresPrincipales;
		this.directores=serie.directores;
		this.ID_Serie=serie.ID_Serie;
		this.precio=serie.precio;
		this.sinopsis=serie.sinopsis;
		this.temporadasDeLaSerie=serie.temporadasDeLaSerie;
		this.tipoDeSerie=serie.tipoDeSerie;
		this.titulo=serie.titulo;
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
		return temporadasDeLaSerie.get(numeroDeTemporada-1);
	}

	public void setTemporadasDeLaSerie(Temporada temporadaDeLaSerie, int numeroDeTemporada) {
		if(temporadasDeLaSerie==null) {
			temporadasDeLaSerie=new ArrayList<Temporada>();
		}
		temporadasDeLaSerie.add(numeroDeTemporada-1, temporadaDeLaSerie);
		temporadaDeLaSerie.setNumeroDeTemporada(numeroDeTemporada-1);
	}

	/*public Calendar getPreviousPriceChangeDate(Calendar currentDate) {
		ArrayList<Calendar> fechas=hp.getDatesFromHistorialPrecios();
		Calendar higherDate=fechas.get(0);
		for(Calendar date:fechas) {
			if(currentDate.after(date) & date.before(higherDate)) {
				higherDate=date;
			}
		}
		return higherDate;
	}*/

	public float getPriceAtTime(Calendar date) {
		Calendar latestDate=Calendar.getInstance();
		latestDate.setTimeInMillis(0);
		for(Calendar dateSetEntry:mapHistorialPrecios.keySet()) {
			if(dateSetEntry.compareTo(date)<dateSetEntry.compareTo(latestDate)){
				latestDate=date;
			}
		}
		//return mapHistorialPrecios.get(date);
		return 0.75f;
	}

}
