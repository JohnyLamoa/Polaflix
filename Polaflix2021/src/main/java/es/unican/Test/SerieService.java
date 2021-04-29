package es.unican.Test;

import org.springframework.beans.factory.annotation.Autowired;

public class SerieService {

	@Autowired
	protected SerieRepository sr;
	
	/*public boolean cerrarSerie(Long id) throws SerieNotFound{
		Serie s = sr.findById(id).orElseThrow(SerieNotFound::new);
	}*/
}
