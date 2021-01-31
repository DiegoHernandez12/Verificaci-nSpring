package springbootholmundo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootholmundo.models.entity.Users;
import springbootholmundo.models.security.Rol;
import springbootholmundo.models.security.UserRole;
import springbootholmundo.repository.IRoles;
import springbootholmundo.repository.IUserRepository;
import springbootholmundo.utils.SecurityUtility;
@Service
public class UserServiceImp implements IUserService{

	@Autowired
	private IUserRepository userepo;
	@Autowired
	private IRoles rolrepo;
	@Override
	public Users findByUsername(String username) {
		// TODO Auto-generated method stub
		return userepo.findByUsername(username);
	}

	@Override
	public Users findByEmail(String email) {
		// TODO Auto-generated method stub
		return userepo.findByEmail(email);
	}

	@Override
	@Transactional
	public Users crearUsuario(String username, String email, String password, List<String> roles, String verificate) {
		Users users = findByUsername(username);
		if(users!=null) {
			return users;
		}else {
			users=new Users();
			users.setUsername(username);
			users.setPassword(SecurityUtility.passwordEncoder().encode(password));
			users.setEmail(email);
			users.setVerificationCode(verificate);
			Set<UserRole> userRoles = new HashSet<>();
			for(String rolnombre: roles) {
				Rol rol = rolrepo.findByNombre(rolnombre);
				if(rol==null) {
					rol=new Rol();
					rol.setNombre(rolnombre);
					rolrepo.save(rol);
				}
				userRoles.add(new UserRole(users, rol));
			}
			users.setUserRoles(userRoles);
			return userepo.save(users);
		}
		
	}

	@Override
	public boolean verificate(String verificatecode) {
		Users  users = userepo.findByVerificationCode(verificatecode);
		if(users==null || users.isEnable()) {
			return false;
		}else {
			userepo.enable(users.getId());
			return true;
		}
		
	}

}
