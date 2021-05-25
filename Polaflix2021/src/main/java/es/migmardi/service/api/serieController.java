package es.migmardi.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.migmardi.repositories.UsuarioRepository;

@RestController
@RequestMapping("/user")
public class serieController {
	@Autowired
	UsuarioRepository ur;
}
