package es.migmardi;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.migmardi.domainModel.Actor;
import es.migmardi.domainModel.EntradaFactura;
import es.migmardi.domainModel.Serie;
import es.migmardi.domainModel.TipoDeAbono;
import es.migmardi.domainModel.TipoDeSerie;
import es.migmardi.domainModel.Usuario;
import es.migmardi.repositories.SerieRepository;
import es.migmardi.repositories.UsuarioRepository;


@Component
public class AppFeeder implements CommandLineRunner {
	
	
	@Autowired
	protected SerieRepository sr;
	
	@Autowired
	protected UsuarioRepository ur;
	
	

	
	@Override
	public void run(String... args) throws Exception {
		Usuario u1 = new Usuario(1L, "Migmardi", "pass", "ES12345", TipoDeAbono.PayPerView);
		Usuario u2 = new Usuario(2L, "JohnyLamoa", "1234", "ES67890", TipoDeAbono.SuscripcionMensual);
		Usuario u3 = new Usuario(3L, "mmd835", "admin", "ES123678", TipoDeAbono.SuscripcionMensual);
		
		Serie s1 = new Serie(1L, "TheBoys", 0.75f, TipoDeSerie.Silver, "Avengers mal");
		Serie s2 = new Serie(2L, "Breaking Bad", 1.0f, TipoDeSerie.Gold, "Quimica");
		Serie s3 = new Serie(3L, "Bojack Horseman", 0.75f, TipoDeSerie.Estandar, "Lorem Ipsum");
		
		Actor a1=new Actor("Brian Cranston", "Heisenberg");
		Actor a2=new Actor("Aaron Paul", "Jessie Pinkman");
		Actor a3=new Actor("Giancarlo Esposito", "Gustavo \"Gus\" Fring");
		
		Set<Actor> setActores2=new HashSet<Actor>();
		setActores2.add(a1);
		setActores2.add(a2);
		setActores2.add(a3);
		
		s2.setActoresPrincipales(setActores2);
		
		sr.save(s1);
		sr.save(s2);
		sr.save(s3);
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.YEAR, 2020);
		cal1.set(Calendar.MONTH, 2);
		cal1.set(Calendar.DAY_OF_MONTH, 3);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, 2020);
		cal2.set(Calendar.MONTH, 2);
		cal2.set(Calendar.DAY_OF_MONTH, 4);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(Calendar.YEAR, 2020);
		cal3.set(Calendar.MONTH, 2);
		cal3.set(Calendar.DAY_OF_MONTH, 5);
		
		EntradaFactura ef1=new EntradaFactura(cal1, s1, 1, 1);
		EntradaFactura ef2=new EntradaFactura(cal2, s1, 1, 2);
		EntradaFactura ef3=new EntradaFactura(cal3, s1, 1, 3);
		
		u1.addEntradaFactura(ef1);
		u1.addEntradaFactura(ef2);
		u1.addEntradaFactura(ef3);
		
		u1.addSerieToListaPendientes(s1);
		u1.addSerieToListaPendientes(s2);
		u1.addSerieToListaPendientes(s3);
		
		ur.save(u1);
		ur.save(u2);
		ur.save(u3);
		
		
		//feedSeries();
		//feedUsuarios();
		
		
		//feedViajes();
		
		//testViajeRepository();
		
		System.out.println("Application feeded");
	}

	private void feedUsuarios() {
		Usuario u1 = new Usuario(1L, "Migmardi", "pass", "ES12345", TipoDeAbono.PayPerView);
		Usuario u2 = new Usuario(2L, "JohnyLamoa", "1234", "ES67890", TipoDeAbono.SuscripcionMensual);
		Usuario u3 = new Usuario(3L, "mmd835", "admin", "ES123678", TipoDeAbono.SuscripcionMensual);
		ur.save(u1);
		ur.save(u2);
		ur.save(u3);
	}
	
	private void feedSeries() {
		Serie s = new Serie(1L, "TheBoys", 0.75f, TipoDeSerie.Silver, "Avengers mal");
		sr.save(s);
	}

	/*private void feedViajes() {
		
		Conductor c = cr.findById("Travis").get();
		
		Localizacion l11 = new PuntoConocido("Santander","Facultad de Ciencias"); 
		Localizacion l12 = new PuntoConocido("Cadiz", "Playa de la Caleta");
		Localizacion l21 = new PuntoConocido("Cadiz", "Playa de la Caleta"); 
		Localizacion l22 = new PuntoConocido("Santander","Facultad de Ciencias");
		
		
		SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");
		Date ida = null;
		Date vuelta = null;
		try {
			ida = dateParser.parse("01-04-2019");
			vuelta = dateParser.parse("07-04-2019");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Viaje v1 = new Viaje(l11,l12,c,ida,6000,8000,3);
		Viaje v2 = new Viaje(l21,l22,c,vuelta,6000,8000,3);
		
		vr.save(v1);
		vr.save(v2);
	}
	
	private void testViajeRepository() {
		
		SimpleDateFormat dateParser = new SimpleDateFormat("dd-MM-yyyy");
		Date sample = null;
		try {
			sample = dateParser.parse("01-01-2020");
		} catch (ParseException e) {
			System.out.println("Crujo parseando fecha");
			e.printStackTrace();
		}
		
		// Set<Viaje> viajes = vr.findByOrigenCiudadAndDestinoCiudad("Santander","Cadiz");
		Set<Viaje> viajes = vr.findByOrigenAndDestino("Santander","Cadiz");
		
		System.out.println("Viajes recuperados = " + viajes.size());
	
		for(Viaje v : viajes) {
			System.out.println("Viaje in " + v.getFecha());
		}
		
		viajes = vr.findByOrigen_CiudadAndFechaBeforeOrderByPrecio("Santander", sample);

		System.out.println("================================");
		
		System.out.println("Viajes recuperados = " + viajes.size());
		
		
//		Usuario paco = ur.findByEmail("paco@carSharing.es"); 
//		
//		System.out.println("Paco = " + paco.getNombre() + ":" + paco.getEmail());
//		
//		Set<Usuario> usuarios = ur.findByFechaAltaAfter(sample);
//		for(Usuario u : usuarios) {
//			System.out.println("Usuario " + u.getNombre() + ":" + u.getEmail());
//		}
		
	}*/

}
