package es.migmardi.service.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonView;

import es.migmardi.domainModel.Factura;
import es.migmardi.domainModel.Usuario;
import es.migmardi.repositories.SerieRepository;
import es.migmardi.repositories.UsuarioRepository;
import es.migmardi.service.ResourceNotFoundException;
import es.migmardi.service.UsuariosMng;


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
	public ResponseEntity<List<Factura>> obtenerFacturacion(@PathVariable("id") long userId) {

		Optional<Usuario> u = ur.findById(userId);
		ResponseEntity<List<Factura>> result;

		if (u.isPresent() && u.get().getAllFacturas().size()>0){
			result = ResponseEntity.ok(u.get().getAllFacturas());
		} else { 
			result = ResponseEntity.notFound().build();
		}

		return result; 	
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value="/{id}/series/{serieID}/visualizar")
	@JsonView(Views.DescripcionUsuario.class)
	public void visualizaCapitulo(@PathVariable("id") long userID, 
			@PathVariable("serieID") long serieID, 
			@RequestParam(required=true) int numTemporada,
			@RequestParam(required=true) int numCapitulo) {
		
		try {
			um.visualizaCapitulo(userID, serieID, numTemporada, numCapitulo);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value="/{id}/series/{serieID}/anhadePendientes")
	@JsonView(Views.DescripcionUsuario.class)
	public void anhadePendiente(@PathVariable("id") long userID, 
			@PathVariable("serieID") long serieID) {
		try {
			um.anhadeSerieAPendiente(userID, serieID);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
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


