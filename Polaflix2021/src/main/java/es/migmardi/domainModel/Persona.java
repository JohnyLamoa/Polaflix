package es.migmardi.domainModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Persona {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idPersona;
	private String nombre;
	
	public Persona(String nmb) {
		setNombre(nmb);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
