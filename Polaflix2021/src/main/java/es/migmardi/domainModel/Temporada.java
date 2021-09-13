package es.migmardi.domainModel;

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
	
	private int numTemporada;
	
	protected Temporada() {}
	
	public Temporada(Map<Integer, Capitulo> caps) {
		capitulos=caps;
	}
	
	public Capitulo getCapituloDeLaSerie(int numCapitulo) {
		return capitulos.get(numCapitulo);
	}

	public void setNumeroDeTemporada(int numeroDeTemporada) {
		this.numTemporada=numeroDeTemporada;
	}
	
	public int getNumeroDeTemporada() {
		return numTemporada;
	}
	
	public int getNumCapitulos() {
		return capitulos.size();
	}
}
