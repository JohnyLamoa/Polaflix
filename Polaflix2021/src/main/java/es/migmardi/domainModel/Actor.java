package es.migmardi.domainModel;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

import es.migmardi.service.api.Views.DescripcionSerie;

@Entity
public class Actor extends Persona{

	@JsonView(DescripcionSerie.class)
	private String personaje;
	
	protected Actor() {}
	
	public Actor(String nmb, String per) {
		super(nmb);
		setPersonaje(per);
	}

	public String getPersonaje() {
		return personaje;
	}

	public void setPersonaje(String personaje) {
		this.personaje = personaje;
	}

}
