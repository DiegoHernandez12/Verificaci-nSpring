package springbootholmundo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import springbootholmundo.models.entity.Users;

public interface IUserRepository extends JpaRepository<Users, Long>{
	@EntityGraph(value="UserComplete", type=EntityGraphType.FETCH)
	public Users findByUsername(String username);
	public Users findByEmail(String correo);
	
	@Transactional
	@Modifying
	@Query("update Users u set u.enable=true where u.id=?1")
	public void enable(Long id);
	@Query("Select u from Users u where u.VerificationCode=?1")
	public Users findByVerificationCode(String code);

}
