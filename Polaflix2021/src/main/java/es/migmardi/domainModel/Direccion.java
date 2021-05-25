package es.migmardi.domainModel;

import javax.persistence.Entity;

@Entity
public class Direccion extends Persona{

	public Direccion(String nmb) {
		super(nmb);
	}

}
