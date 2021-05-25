package es.migmardi.domainModel;

import javax.persistence.Entity;

@Entity
public class Actor extends Persona{

	private String personaje;
	
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
