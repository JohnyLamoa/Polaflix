package es.migmardi.domainModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonView;

import es.migmardi.service.api.Views.DescripcionSerie;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Persona {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idPersona;
	@JsonView(DescripcionSerie.class)
	private String nombre;
	
	protected Persona() {}
	
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
