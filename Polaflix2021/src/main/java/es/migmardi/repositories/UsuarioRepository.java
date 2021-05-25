package es.migmardi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.migmardi.domainModel.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
