package es.migmardi.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

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
		
	@PersistenceUnit
	EntityManagerFactory emf;
	@Autowired
	SerieRepository sr;
	@Autowired
	UsuarioRepository ur;
	
	
	@Transactional
	public void visualizaCapitulo(long userID,long serieID, int numTemporada, int numCapitulo) throws ResourceNotFoundException {
		
		Usuario user = ur.findById(userID).orElseThrow(ResourceNotFoundException::new);
		Serie serie = sr.findById(serieID).orElseThrow(ResourceNotFoundException::new); 
		
		Temporada temporada = serie.getTemporadaDeLaSerie(numTemporada);
		Capitulo capitulo = temporada.getCapituloDeLaSerie(numCapitulo);
		
		user.visualizaCapitulo(serie, temporada, capitulo);
	}
	
	@Transactional
	public Usuario anhadeSerieAPendiente(long userID, long idSerie) throws ResourceNotFoundException {
		
		Usuario user = ur.findById(userID).orElseThrow(ResourceNotFoundException::new);
		Serie serie = sr.findById(idSerie).orElseThrow(ResourceNotFoundException::new);
		user.addSerieToListaPendientes(serie);
		return user;
	}

}
