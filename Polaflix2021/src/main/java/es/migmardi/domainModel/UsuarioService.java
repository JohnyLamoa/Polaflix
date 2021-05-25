package es.migmardi.domainModel;

import org.springframework.beans.factory.annotation.Autowired;

import es.migmardi.repositories.UsuarioRepository;

public class UsuarioService {

	@Autowired
	protected UsuarioRepository ur;
	
	/*public boolean cerrarUsuario(Long id) throws UsuarioNotFound{
		Usuario u = ur.findById(id).orElseThrow(UsuarioNotFound::new);
	}*/
}
