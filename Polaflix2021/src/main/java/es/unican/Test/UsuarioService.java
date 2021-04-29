package es.unican.Test;

import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioService {

	@Autowired
	protected UsuarioRepository ur;
	
	/*public boolean cerrarUsuario(Long id) throws UsuarioNotFound{
		Usuario u = ur.findById(id).orElseThrow(UsuarioNotFound::new);
	}*/
}
