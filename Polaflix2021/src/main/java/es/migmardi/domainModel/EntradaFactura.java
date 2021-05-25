package es.migmardi.domainModel;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EntradaFactura {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idEntradaFactura;
	private Calendar fechaVisualizacion;
	@OneToOne(cascade=CascadeType.DETACH)
	private Serie serie;
	private int numTemporada;
	private int numCapitulo;

	public EntradaFactura() {}

	private Capitulo getCapitulo() {
		return serie.getTemporadaDeLaSerie(numTemporada).getCapituloDeLaSerie(numCapitulo);
	}
	
	public float getPrice() {
		//Obtener todos los precios de los episodios vistos desde 
		//el principio del mes hasta fechaVisualizacion, junto con su 
		//precio en ese momento
		//se que así falla poruqe las fechas deberían ser exactas
		Calendar previousDateWhenPriceChanged = serie.getPreviousPriceChangeDate(fechaVisualizacion);
		return serie.getPriceAtTime(previousDateWhenPriceChanged);

	}
}
