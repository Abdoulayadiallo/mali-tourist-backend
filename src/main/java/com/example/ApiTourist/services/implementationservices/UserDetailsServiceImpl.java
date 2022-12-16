package com.example.ApiTourist.services.implementationservices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.transaction.Transactional;

import com.example.ApiTourist.model.Utilisateur;
import com.example.ApiTourist.model.UtilisateurRole;
import com.example.ApiTourist.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur utilisateur = accountService.findByUsername(username);
		if (utilisateur == null) {
			throw new UsernameNotFoundException("Username " + username + " non trouvé");
		}
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		Set<UtilisateurRole> userRoles = utilisateur.getUtilisateurRoles();
		userRoles.forEach(userRole -> {
			authorities.add(new SimpleGrantedAuthority(userRoles.toString()));
		});
		return new User(utilisateur.getUsername(), utilisateur.getPassword(), authorities);
	}

}