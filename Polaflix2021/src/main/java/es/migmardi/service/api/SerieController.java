package es.migmardi.service.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import com.fasterxml.jackson.annotation.JsonView;

import es.migmardi.domainModel.Serie;
import es.migmardi.repositories.SerieRepository;
import es.migmardi.service.api.Views.DescripcionSerie;



@RestController
@RequestMapping("/series")
public class SerieController {
	@Autowired
	SerieRepository sr;
	
	Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/")
	@JsonView(Views.DescripcionSerie.class)
	List<Serie> obtenerAllSeries() {
		return sr.findAll();
	}

	/*
	@JsonView(Views.DescripcionSerie.class)
		
	public ResponseEntity<List<Serie>> obtenerAllSeries() {
		
		ResponseEntity<List<Serie>> result;
		
		result = ResponseEntity.ok(sr.findAll());

		return result; 	
	}*/
	
	@GetMapping(value="/{id}")
	@JsonView(Views.DescripcionSerie.class)
		
	public ResponseEntity<Serie> obtenerSerie(@PathVariable("id") long serieId) {
		
		Optional<Serie> s = sr.findById(serieId);
		ResponseEntity<Serie> result;
		
		if (s.isPresent()) {
			result = ResponseEntity.ok(s.get());
		} else { 
			result = ResponseEntity.notFound().build();
		}

		return result; 	
	}
	
	
}
