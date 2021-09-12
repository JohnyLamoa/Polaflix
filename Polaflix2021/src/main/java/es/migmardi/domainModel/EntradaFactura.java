package es.migmardi.domainModel;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

import es.migmardi.service.api.Views.DescripcionSerie;
import es.migmardi.service.api.Views.DescripcionUsuario;

@Entity
public class EntradaFactura {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idEntradaFactura;
	@JsonView(DescripcionUsuario.class)
	private Calendar fechaVisualizacion;
	@JsonView(DescripcionUsuario.class)
	@OneToOne(cascade=CascadeType.DETACH)
	private Serie serie;
	@JsonView(DescripcionUsuario.class)
	private int numTemporada;
	@JsonView(DescripcionUsuario.class)
	private int numCapitulo;
	@JsonView(DescripcionUsuario.class)
	private float importe;


	protected EntradaFactura() {}
	
	public EntradaFactura(Calendar fechaVisualizacion, Serie serie, int numTemporada, int numCapitulo){
		this.fechaVisualizacion=fechaVisualizacion;
		this.serie=serie;
		this.numTemporada=numTemporada;
		this.numCapitulo=numCapitulo;
		importe=getPrice(Calendar.getInstance());
	}
	
	private Capitulo getCapitulo() {
		return serie.getTemporadaDeLaSerie(numTemporada).getCapituloDeLaSerie(numCapitulo);
	}
	
	public float getPrice(Calendar date) {
		return serie.getPriceAtTime(date);
	}

	public Calendar getFechaVisualizacion() {
		return fechaVisualizacion;
	}
}
