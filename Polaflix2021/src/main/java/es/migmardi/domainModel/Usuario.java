package es.migmardi.domainModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonView;

import es.migmardi.service.api.Views.DescripcionUsuario;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(DescripcionUsuario.class)
	private long ID_Usuario;
	@JsonView(DescripcionUsuario.class)
	private String nombreDeUsuario;
	private String contrasegna;
	private String IBAN;
	@JsonView(DescripcionUsuario.class)
	private TipoDeAbono abono;
	@OneToMany
	@JsonView(DescripcionUsuario.class)
	private List<Serie> listaSeriesPendientes;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JsonView(DescripcionUsuario.class)
	private List<SerieComenzada> listaSeriesComenzadas;
	
	@OneToMany
	@JsonView(DescripcionUsuario.class)
	private List<Serie> listaSeriesVistas;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JsonView(DescripcionUsuario.class)
	private List<Factura> facturasPasadas;
	

	protected Usuario() {
	}

	public Usuario(long IDu,String nDU, String con, String IBAN, TipoDeAbono abono) {
		this.ID_Usuario=IDu;
		this.IBAN = IBAN;
		this.setNombreDeUsuario(nDU);
		this.setContrasegna(con);
		this.setTipoDeAbono(abono);
		listaSeriesPendientes=new ArrayList<Serie>();
		listaSeriesComenzadas=new ArrayList<SerieComenzada>();
		listaSeriesVistas=new ArrayList<Serie>();
		facturasPasadas=new ArrayList<Factura>();
		facturasPasadas.add(new Factura(Calendar.MONTH, Calendar.YEAR, 0.0f));
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
		//El método para agregar una serie a pendientes debería verificar que la serie 
		//no esté ya ni en pendientes, ni en empezadas ni en terminadas.  
		if(listaSeriesComenzadas.contains(serie)) {
			removeSerieFromListaComenzadas((SerieComenzada)serie);
		}
		if(listaSeriesVistas.contains(serie)) {
			removeSerieFromListaVistas(serie);
		}
		if(listaSeriesPendientes.contains(serie)) {
			removeSerieFromListaPendientes(serie);
		}
		listaSeriesPendientes.add(serie);
	}

	//Funcionalidad acesible al usuario, no se le mostrarán como series pendientes las que no estén en esta lista
	public void removeSerieFromListaPendientes(Serie serie) {		
		listaSeriesPendientes.add(serie);
	}

	private void addSerieToListaComenzadas(Serie serie, int utv, int ucv) {
		SerieComenzada serie1 = new SerieComenzada(serie, utv, ucv);
		listaSeriesComenzadas.add(serie1);
	}

	public void removeSerieFromListaComenzadas(Serie ser) {
		listaSeriesComenzadas.remove(ser);
		
	}
	
	/*
	public SerieComenzada getSerieComenzadaFromListaSeriesComenzadas(Serie serie){
		for(SerieComenzada s:listaSeriesComenzadas){
			if(s.getSerie()==serie) {
				return s;
			}
		}
		return null;
	}*/
	
	public int getIndexSerieComenzadaFromListaSeriesComenzadas(Serie serie){
		for(int i=0; i<listaSeriesComenzadas.size()-1; i++){
			if(listaSeriesComenzadas.get(i).getSerie()==serie) {
				return i;
			}
		}
		return -1;
	}

	public void addSerieToListaVistas(Serie serie) {
		listaSeriesComenzadas.remove(serie);//Si o si, para terminar una serie ha de haber sido comenzada previamente
		listaSeriesVistas.add(serie);
	}

	public void removeSerieFromListaVistas(Serie serie) {
		listaSeriesVistas.remove(serie);
	}

	public void visualizaCapitulo(Serie ser, Temporada temp, Capitulo cap) {
		
		cap.setVisto(true);
		
		if (listaSeriesPendientes.contains(ser)){
			listaSeriesPendientes.remove(ser);
		}
		
		if(listaSeriesComenzadas.contains(ser)) {
			if(ser.getNumTemporadas()==temp.getNumeroDeTemporada() & temp.getNumCapitulos()==cap.getNumeroDeCapitulo()) {
				removeSerieFromListaComenzadas(ser);
				addSerieToListaVistas(ser);
			}else{
				int indexSerie=getIndexSerieComenzadaFromListaSeriesComenzadas(ser);
				listaSeriesComenzadas.get(indexSerie).setUltimaTemporadaVista(temp.getNumeroDeTemporada());
				listaSeriesComenzadas.get(indexSerie).setUltimoCapituloVisto(cap.getNumeroDeCapitulo());
			}
		}else {
			addSerieToListaComenzadas(ser, temp.getNumeroDeTemporada(), cap.getNumeroDeCapitulo());
		}
	}
	
	public float getFacturacion() {
		float precioTotal=0.0f;
		for(EntradaFactura fact:facturasPasadas.get(facturasPasadas.size()-1).getElementosFactura()) {
			precioTotal+=fact.getPrice(fact.getFechaVisualizacion());
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
		facturasPasadas.add(new Factura(Calendar.MONTH, Calendar.YEAR, 0.0f));
	}

	public void addEntradaFactura(EntradaFactura ef) {
		facturasPasadas.get(facturasPasadas.size()-1).addElementoFactura(ef);
	}

	public List<Factura> getAllFacturas(){
		return facturasPasadas;
	}
	
	public Factura getFacturasMesActual(){
		return facturasPasadas.get(facturasPasadas.size()-1);
	}
	
}
