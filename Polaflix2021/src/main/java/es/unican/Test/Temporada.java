package es.unican.Test;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Temporada {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idTemporada;
	@OneToMany(cascade=CascadeType.ALL)
	private Map<Integer, Capitulo> capitulos;
	
	protected Temporada() {}
	
	public Temporada(Map<Integer, Capitulo> caps) {
		capitulos=caps;
	}
	
	public Capitulo getCapituloDeLaSerie(int numCapitulo) {
		return capitulos.get(numCapitulo);
	}
}
