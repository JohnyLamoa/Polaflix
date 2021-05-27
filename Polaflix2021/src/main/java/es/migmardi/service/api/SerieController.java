package es.migmardi.service.api;

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

import es.migmardi.domainModel.Usuario;
import es.migmardi.repositories.UsuarioRepository;



@RestController
@RequestMapping("/series")
public class SerieController {
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
	
	
}
