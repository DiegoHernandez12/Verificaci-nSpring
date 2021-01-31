package springbootholmundo.service;

import java.util.List;

import springbootholmundo.models.entity.Users;

public interface IUserService {
public Users findByUsername(String username);
public Users findByEmail(String email);
public Users crearUsuario(String username, String email, String password, List<String> roles, String verificate);
public boolean verificate(String verificatecode);
}
