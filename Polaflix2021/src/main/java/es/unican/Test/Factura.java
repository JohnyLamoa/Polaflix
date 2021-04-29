package es.unican.Test;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Factura {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idFactura;
	private int numMes;
	private int anho;
	private float coste;
	@OneToMany(cascade=CascadeType.REMOVE)
	private List<EntradaFactura> elementosFactura;
	
	protected Factura() {}
	
	public Factura(int nM, int a, float cos, List<EntradaFactura> eF) {
		numMes=nM;
		anho=a;
		coste=cos;
		elementosFactura = eF;
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

	public List<EntradaFactura> getElementosFactura() {
		return elementosFactura;
	}
}
