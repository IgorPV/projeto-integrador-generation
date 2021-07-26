package com.educati.EducaTI.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.educati.EducaTI.model.Usuario;
import com.educati.EducaTI.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Usuario> usuarioExistente = repository.findByEmail(email);
		usuarioExistente.orElseThrow(() -> new UsernameNotFoundException(email + "Email não cadastrado."));
		
		return usuarioExistente.map(UserDetailsImpl::new).get();
	}
	
	

}
