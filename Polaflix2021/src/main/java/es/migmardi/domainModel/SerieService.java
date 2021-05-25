package es.migmardi.domainModel;

import org.springframework.beans.factory.annotation.Autowired;

import es.migmardi.repositories.SerieRepository;

public class SerieService {

	@Autowired
	protected SerieRepository sr;
	
	/*public boolean cerrarSerie(Long id) throws SerieNotFound{
		Serie s = sr.findById(id).orElseThrow(SerieNotFound::new);
	}*/
}
