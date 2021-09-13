package es.migmardi.domainModel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import es.migmardi.service.api.Views.DescripcionUsuario;

@Entity
public class Factura {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idFactura;
	@JsonView(DescripcionUsuario.class)
	private int numMes;
	@JsonView(DescripcionUsuario.class)
	private int anho;
	@JsonView(DescripcionUsuario.class)
	private float coste;
	@JsonView(DescripcionUsuario.class)
	@OneToMany(cascade=CascadeType.ALL)
	private List<EntradaFactura> elementosFactura;
	
	protected Factura() {}
	
	public Factura(int nM, int a, float cos) {
		numMes=nM;
		anho=a;
		coste=cos;
		elementosFactura = new ArrayList<EntradaFactura>(); 
	}

	public int getNumMes() {
		return numMes;
	}

	public int getAnho() {
		return anho;
	}

	public float getCoste() {
		return coste;
	}

	public void addElementoFactura(EntradaFactura ef) {
		elementosFactura.add(ef);
	}
	
	public List<EntradaFactura> getElementosFactura() {
		return elementosFactura;
	}
	
	
}
