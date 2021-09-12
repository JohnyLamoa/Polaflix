package es.migmardi.service.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonView;

import es.migmardi.domainModel.EntradaFactura;
import es.migmardi.domainModel.Factura;
import es.migmardi.domainModel.Serie;
import es.migmardi.domainModel.Usuario;
import es.migmardi.repositories.SerieRepository;
import es.migmardi.repositories.UsuarioRepository;
import es.migmardi.service.UsuariosMng;
import es.migmardi.service.api.Views.DescripcionUsuario;


@RestController
@RequestMapping("/users")
public class UsuarioController {
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	SerieRepository sr;
	
	@Autowired
	UsuariosMng um;

	

	Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/{id}")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") long userId) {

		Optional<Usuario> u = ur.findById(userId);
		ResponseEntity<Usuario> result;

		if (u.isPresent()) {
			result = ResponseEntity.ok(u.get());
		} else { 
			result = ResponseEntity.notFound().build();
		}

		return result; 	
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/{id}/facturacion")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<List<EntradaFactura>> obtenerFacturacion(@PathVariable("id") long userId) {

		Optional<Usuario> u = ur.findById(userId);
		ResponseEntity<List<EntradaFactura>> result;

		if (u.isPresent() && u.get().getFacturasMesActual().size()>0){
			result = ResponseEntity.ok(u.get().getFacturasMesActual());
		} else { 
			result = ResponseEntity.notFound().build();
		}

		return result; 	
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value="/{id}/series/{serieID}/visualizar")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<Usuario> visualizaCapitulo(@PathVariable("id") long userID, 
			@PathVariable("serieID") long serieID, 
			@RequestParam(required=true) int numTemporada,
			@RequestParam(required=true) int numCapitulo) {
		
		ResponseEntity<Usuario> result = null;
		
		try {
			Usuario f = um.visualizaCapitulo(userID, serieID, numTemporada, numCapitulo);
			if (f != null) {
				result = ResponseEntity.ok(f);
			} else {
				result = new ResponseEntity<Usuario>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			result = ResponseEntity.notFound().build();
		}
		return result;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value="/{id}/series/{serieID}/anhadePendientes")
	@JsonView(Views.DescripcionUsuario.class)
	public void anhadePendiente(@PathVariable("id") long userID, 
			@PathVariable("serieID") long serieID) {
		
		ResponseEntity<Usuario> result = null;
		Optional<Usuario> u = ur.findById(userID);
		Optional<Serie> s = sr.findById(serieID);
		u.get().addSerieToListaPendientes(s.get());
	}

	
	/*@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value="/{id}/facturacion")
	@JsonView(Views.DescripcionUsuario.class)
	public ResponseEntity<List<Factura>> obtenerFacturacion(@PathVariable("id") long userId) {

		Optional<Usuario> u = ur.findById(userId);
		ResponseEntity<List<Factura>> result;

		if (u.isPresent() && u.get().getAllFacturas().size()!=0) {
			result = ResponseEntity.ok(u.get().getAllFacturas());
		} else { 
			result = ResponseEntity.notFound().build();
		}

		return result; 	
	}*/
}


