package es.migmardi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.migmardi.domainModel.Capitulo;
import es.migmardi.domainModel.Serie;
import es.migmardi.domainModel.Temporada;
import es.migmardi.domainModel.Usuario;
import es.migmardi.repositories.SerieRepository;
import es.migmardi.repositories.UsuarioRepository;



@Service
public class UsuariosMng {
	
	class ResourceNotFound extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	}
	
	@Autowired
	SerieRepository sr;
	@Autowired
	UsuarioRepository ur;
	
	
	@Transactional
	public Usuario visualizaCapitulo(long userID,long numIDSerie, int numTemporada, int numCapitulo) throws ResourceNotFound {
		
		Usuario user = ur.findById(userID).orElseThrow(ResourceNotFound::new);
		Serie serie = sr.findById(numIDSerie).orElseThrow(ResourceNotFound::new); //accedidas al repositorio
		
		Temporada temporada = serie.getTemporadaDeLaSerie(numTemporada);
		Capitulo capitulo = temporada.getCapituloDeLaSerie(numCapitulo);
		
		user.visualizaCapitulo(serie, temporada.getNumeroDeTemporada(), capitulo.getNumeroDeCapitulo());
		
		return user;
	}
	
	@Transactional
	public Usuario anhadeSerieAPendiente(long userID, long idSerie) throws ResourceNotFound {
		
		Usuario user = ur.findById(userID).orElseThrow(ResourceNotFound::new);
		Serie serie = sr.findById(idSerie).orElseThrow(ResourceNotFound::new);
		user.addSerieToListaPendientes(serie);
		return user;
	}

}
