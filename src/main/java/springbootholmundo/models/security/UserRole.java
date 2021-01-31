package springbootholmundo.models.security;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;


import springbootholmundo.models.entity.Users;

@Entity

public class UserRole {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userRoleId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private Users user;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id")
	private Rol rol;
	public UserRole(Users users, Rol rol) {
	
		this.user = users;
		this.rol = rol;
	}
	
	public UserRole() {
		
	}
	public Long getUserRoleid() {
		return userRoleId;
	}
	public void setUserRoleid(Long userRoleid) {
		this.userRoleId = userRoleid;
	}
	public Users getUsers() {
		return user;
	}
	public void setUsers(Users users) {
		this.user = users;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	
	
	

}
