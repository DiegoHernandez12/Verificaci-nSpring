package springbootholmundo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootholmundo.models.security.Rol;

public interface IRoles extends JpaRepository<Rol, Long>{
	public Rol findByNombre(String nombre);

}
