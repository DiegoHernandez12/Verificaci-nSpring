package springbootholmundo.models.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import springbootholmundo.models.security.Authority;
import springbootholmundo.models.security.UserRole;
@Entity
@NamedEntityGraph(
		
		name= "UserComplete",
		attributeNodes= { @NamedAttributeNode(value="userRoles", subgraph="role-subgraph") },
		subgraphs= { 
			@NamedSubgraph(name = "role-subgraph", attributeNodes = {  @NamedAttributeNode("rol") }
		)}
	)

@SuppressWarnings("serial")
public class Users implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long id;
	private String username;
	private String password;
	private String email;
	private boolean enable;
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();
	@Column(length=64, nullable=true, updatable=false, name="verificate_code")

	private String VerificationCode;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		userRoles.forEach(userRole->authorities.add(new Authority(userRole.getRol().getNombre())));
		
		return authorities;
	}



	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public boolean isEnable() {
		return enable;
	}



	public void setEnable(boolean enable) {
		this.enable = enable;
	}



	public Set<UserRole> getUserRoles() {
		return userRoles;
	}



	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}



	public String getVerificationCode() {
		return VerificationCode;
	}



	public void setVerificationCode(String verificationCode) {
		VerificationCode = verificationCode;
	}


	

}
