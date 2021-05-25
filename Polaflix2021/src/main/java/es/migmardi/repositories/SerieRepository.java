package es.migmardi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.migmardi.domainModel.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long>{

}