package br.com.estefanosantos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario user = usuarioRepository.findUserByLogin(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado.");
		}
		
		return new User(user.getUsername(), user.getPassword(), user.getAuthorities()) ;
	}

}
