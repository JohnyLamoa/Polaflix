package es.unican.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ID_Usuario;
	private String nombreDeUsuario;
	private String contrasegna;
	private String IBAN;
	private TipoDeAbono abono;
	@OneToMany
	private List<Serie> listaSeriesPendientes;
	@OneToMany
	private List<SerieComenzada> listaSeriesComenzadas;
	@OneToMany
	private List<Serie> listaSeriesVistas;
	@OneToMany
	private List<Factura> facturasPasadas;
	@OneToMany
	private List<EntradaFactura> facturacionMesActual;


	protected Usuario() {
	}
	
	public Usuario(String nDU, String con, String IBAN, TipoDeAbono abono) {
		this.IBAN = IBAN;
		this.setNombreDeUsuario(nDU);
		this.setContrasegna(con);
		this.setTipoDeAbono(abono);
		listaSeriesPendientes=new ArrayList<Serie>();
		listaSeriesComenzadas=new ArrayList<SerieComenzada>();
		listaSeriesVistas=new ArrayList<Serie>();
	}

	//Getters y setters varios

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public String getContrasegna() {
		return contrasegna;
	}

	public void setContrasegna(String contrasegna) {
		this.contrasegna = contrasegna;
	}

	public String getIBAN() {
		return IBAN;
	}

	public TipoDeAbono getTipoDeAbono() {
		return abono;
	}

	public void setTipoDeAbono(TipoDeAbono newTipoDeAbono){
		abono=newTipoDeAbono;
	}

	//Agnadir y eliminar series a las listas vistas, pendientes y comenzdas

	public void addSerieToListaPendientes(Serie serie) {
		listaSeriesPendientes.add(serie);
	}

	//Funcionalidad acesible al usuario, no se le mostrarán como series pendientes las que no estén en esta lista
	public void removeSerieFromListaPendientes(Serie serie) {		
		listaSeriesPendientes.add(serie);
	}

	public void addSerieToListaComenzadas(SerieComenzada serie) {
		if(listaSeriesPendientes.contains(serie)) {//Es posible comenzar una serie que no esté en la lista de pendientes
			listaSeriesPendientes.remove(serie);
		}
		listaSeriesComenzadas.add(serie);
	}
	
	public void removeSerieFromListaComenzadas(SerieComenzada serie) {
		listaSeriesComenzadas.remove(serie);
	}

	public void addSerieToListaVistas(Serie serie) {
		listaSeriesComenzadas.remove(serie);//Si o si, para terminar una serie ha de haber sido comenzada previamente
		listaSeriesVistas.add(serie);
	}
	
	public void removeSerieFromListaVistas(Serie serie) {
		listaSeriesVistas.remove(serie);
	}

	public float getFacturacion() {
		float precioTotal=0.0f;
		for(EntradaFactura fact:facturacionMesActual) {
			precioTotal+=fact.getPrice();
		}
		return precioTotal;
	}

	public float getFacturaCoste() {
		if(abono==TipoDeAbono.SuscripcionMensual) {
			return 20.0f;
		}
		return getFacturacion();
	}
	
	public void addFactura() {
		facturasPasadas.add(
				new Factura(Calendar.MONTH, Calendar.YEAR, 
				getFacturaCoste(), (ArrayList<EntradaFactura>) facturacionMesActual));
		facturacionMesActual.clear();
	}




}
