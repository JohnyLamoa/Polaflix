package es.migmardi.service.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import com.fasterxml.jackson.annotation.JsonView;

import es.migmardi.domainModel.Factura;
import es.migmardi.domainModel.Usuario;
import es.migmardi.repositories.UsuarioRepository;
import es.migmardi.service.api.Views.DescripcionUsuario;



@RestController
@RequestMapping("/users")
public class UsuarioController {
	@Autowired
	UsuarioRepository ur;

	Logger logger = LoggerFactory.getLogger(UsuarioController.class);

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
	}
}


