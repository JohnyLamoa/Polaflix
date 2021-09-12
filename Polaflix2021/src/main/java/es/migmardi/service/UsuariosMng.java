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
		
	@Autowired
	SerieRepository sr;
	@Autowired
	UsuarioRepository ur;
	
	
	@Transactional
	public Usuario visualizaCapitulo(long userID,long serieID, int numTemporada, int numCapitulo) throws ResourceNotFoundException {
		
		Usuario user = ur.findById(userID).orElseThrow(ResourceNotFoundException::new);
		Serie serie = sr.findById(serieID).orElseThrow(ResourceNotFoundException::new); 
		
		Temporada temporada = serie.getTemporadaDeLaSerie(numTemporada);
		Capitulo capitulo = temporada.getCapituloDeLaSerie(numCapitulo);
		
		user.addSerieToListaComenzadas(serie, temporada.getNumeroDeTemporada(), capitulo.getNumeroDeCapitulo());
		
		return user;
	}
	
	@Transactional
	public Usuario anhadeSerieAPendiente(long userID, long idSerie) throws ResourceNotFoundException {
		
		Usuario user = ur.findById(userID).orElseThrow(ResourceNotFoundException::new);
		Serie serie = sr.findById(idSerie).orElseThrow(ResourceNotFoundException::new);
		user.addSerieToListaPendientes(serie);
		return user;
	}

}
